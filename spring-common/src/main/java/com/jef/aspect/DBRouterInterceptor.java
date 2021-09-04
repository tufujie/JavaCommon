package com.jef.aspect;

import com.jef.common.context.SpringContextHolder;
import com.jef.dbRouting.DBRouter;
import com.jef.dbRouting.DbContextHolder;
import com.jef.dbRouting.annotation.Router;
import com.jef.dbRouting.annotation.RouterConstants;
import com.jef.dbRouting.router.DBRouterImpl;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 切面切点 在Router注解的方法执行前执行 切点织入
 * @autohr Jef
 */
@Aspect
@Component
public class DBRouterInterceptor {
    private static Logger logger = LogManager.getLogger(DBRouterInterceptor.class);

    private DBRouter dBRouter;

    @Pointcut("@annotation(com.jef.dbRouting.annotation.Router)")
    public void aopPoint() {
    }

    @Before("aopPoint()")
    public Object doRoute(JoinPoint jp) throws Throwable {
        boolean result = true;
        if (DbContextHolder.DATA_SOURCE_READ.equals(DbContextHolder.getDbKey())) {
            return result;
        }
        long t1 = System.currentTimeMillis();
        Method method = getMethod(jp);
        Router router = method.getAnnotation(Router.class);
        String routeField = router.routerField();
        Object[] args = jp.getArgs();
        setDBRouter();
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                String routeFieldValue = BeanUtils.getProperty(arg, routeField);
                logger.debug("routeFieldValue={}", routeFieldValue);
                if (StringUtils.isNotEmpty(routeFieldValue)) {
                    if (RouterConstants.ROUTER_FIELD_DEFAULT.equals(routeField)) {
                        dBRouter.doRouteByResource(routeFieldValue);
                        break;
                    } else {
                        this.searchParamCheck(routeFieldValue);
                        String resource = routeFieldValue.substring(routeFieldValue.length() - 4);
                        dBRouter.doRoute(Integer.valueOf(resource));
                        break;
                    }
                }
            }
        }
        logger.debug("doRouteTime={}ms", (System.currentTimeMillis() - t1));
        return result;
    }

    private Method getMethod(JoinPoint jp) throws NoSuchMethodException {
        Signature sig = jp.getSignature();
        MethodSignature msig = (MethodSignature) sig;
        return getClass(jp).getMethod(msig.getName(), msig.getParameterTypes());
    }

    private Class<? extends Object> getClass(JoinPoint jp)
            throws NoSuchMethodException {
        return jp.getTarget().getClass();
    }


    /**
     * 查询支付结构参数检查
     *
     * @param payId
     */
    private void searchParamCheck(String payId) {
        if ("".equals(payId.trim())) {
            throw new IllegalArgumentException("payId is empty");
        }
    }

    public DBRouter getdBRouter() {
        return dBRouter;
    }

    /**
     * 设置路由
     * @author Jef
     * @date 2021/4/4
     */
    public void setDBRouter() {
        if (dBRouter == null) {
            DBRouterImpl dBRouterImpl = SpringContextHolder.getBean("dBRouter");
            this.dBRouter = dBRouterImpl;
        }
    }

}
