package com.jef.aspect;
import com.jef.annotation.DataSourceMarker;
import com.jef.dbRouting.DbContextHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.Method;
/**
 * 读写分离切面拦截，使用读数据源的2种情况：<p>
 * 1）发现service中的方法有Transactional注解修饰时，且readOnly()==true时；<br>
 * 2）当service的方法以get, select, query, find, export, fetch, is, page, search, count开头时，启用读数据源；<br>
 * 不满足以上两种条件时，启用写数据源。
 *
 * <p>
 *     <b>注意：</b>实现Ordered接口是用来做切面拦截排序用的，当getOrder()越小越早执行，spring-mybatis.xml里定义<tx:annotation-driven>有用到，
 * </p>
 * @author Jef
 * @date 2021/03/29
 * //@EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass=true)
 */
@Aspect
@Component
public class ReadWriteDataSourceAspect implements Ordered {
        private final Logger logger = LogManager.getLogger(ReadWriteDataSourceAspect.class);

        @Pointcut("execution(* com.jef.service.impl.*.*(..))")
        public void serviceAspect() {

        }

        @Before("serviceAspect() && !@annotation(com.jef.annotation.NoDynamicDataSource)")
        public void before(JoinPoint point) throws Throwable {
            MethodSignature methodSignature = (MethodSignature)point.getSignature();
            Method method = methodSignature.getMethod();

            try {
                Transactional transactional = AnnotatedElementUtils.findMergedAnnotation(method, Transactional.class);
                if (transactional != null) {
                    if (transactional.readOnly()) {
                        read(method);
                    } else {
                        write(point);
                    }
                    return;
                }

                DataSourceMarker dataSourceMarker = AnnotationUtils.findAnnotation(method, DataSourceMarker.class);
                if (dataSourceMarker != null) {
                    if (dataSourceMarker.readOnly()) {
                        read(method);
                    } else {
                        write(point);
                    }
                    return;
                }

                if (guessIsReadMethod(method)) {
                    read(method);
                } else {
                    write(point);
                }
            } catch (Exception e) {
                logger.error("set dynamic datasource arise error"+e.getMessage(), e);
            }
        }

        private void write(JoinPoint point) throws Throwable {
            new DBRouterInterceptor().doRoute(point);
        }

        private void read(Method method) {
            logger.debug("put_data_source,{},{}", method.getName(), DbContextHolder.DATA_SOURCE_READ);
            DbContextHolder.setDbKey(DbContextHolder.DATA_SOURCE_READ);
        }

        /**
         * 推测是否是只读方法，以方法名的第一个单词推测是，目前包含范围有：get, select, query, find, export, fetch, is, page, search, count
         * @author Jef
         * @date 2021/3/29
         * @param method
         * @return boolean
         */
        private boolean guessIsReadMethod(Method method) {
            String methodName = method.getName();
            return methodName.startsWith("get") || methodName.startsWith("select") || methodName.startsWith("query")
                    || methodName.startsWith("find") || methodName.startsWith("export") || methodName.startsWith("fetch")
                    || methodName.startsWith("is") || methodName.startsWith("page") || methodName.startsWith("search")
                    || methodName.startsWith("count");
        }

        @Override
        public int getOrder() {
            return 0;
        }
}