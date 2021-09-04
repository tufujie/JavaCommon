package com.jef.aspect;

import com.jef.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Jef
 * @date 2020/12/30
 */
//@Component
//@Aspect
public class MyAspect {
    public static Logger logger = LogManager.getLogger(MyAspect.class);
    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<Map<String, Object>>();

    public MyAspect() {
        System.out.println("切面控制台输出");
        logger.info("切面控制台输出test");
    }

    /**
     * 切入点表达式
     * 其中 com.jef.controller.*.* 是根据实际设置
     * @author Jef
     * @date 2020/12/30
     */
    @Pointcut("execution(* com.jef.controller.LoginController.*(..))")
    private void controllerPoint() {
        // 切入点签名
        System.out.println("切入点签名");
    }

    /**
     * 在before的时候初始化
     */
    @Before("controllerPoint()")
    public void doBefore() {
        try {
            logger.info("方法开始执行前执行");
            threadLocal.remove();
            Map<String, Object> map = new ConcurrentHashMap<>();
            threadLocal.set(map);
        } catch (Exception e) {
            logger.error("error", e);
        }
    }

    /**
     * 方法结束执行后的操作
     */
    @AfterReturning("controllerPoint()")
    public void doAfterReturning(){
        logger.info("返回通知，方法结束执行后的操作");
    }

    /**
     * 在after的时候清理
     */
    @After("controllerPoint()")
    public void doAfter() {
        try {
            logger.info("方法结束执行的时候清理");
            threadLocal.remove();
        } catch (Exception e) {
            logger.error("error", e);
        }
    }

    /**
     * 方法有异常时的操作
     */
    @AfterThrowing("controllerPoint()")
    public void doAfterThrow(){
        logger.info("方法有异常时的操作");
    }

    /**
     * 方法执行
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("controllerPoint()")
    public Object aoAround(ProceedingJoinPoint pjp) throws Throwable {
        logger.info("方法执行");
        return null;
        /*//日志实体对象
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //获取当前登陆用户信息
        User loginUser = SessionUtil.getLoginSession(request);
        if(loginUser==null){
            log.setLoginAccount("—— ——");
        }else{
            log.setLoginAccount(loginUser.getUserAuth().getIdentity());
        }

        // 拦截的实体类，就是当前正在执行的controller
        Object target = pjp.getTarget();
        // 拦截的方法名称。当前正在执行的方法
        String methodName = pjp.getSignature().getName();
        // 拦截的方法参数
        Object[] args = pjp.getArgs();
        // 拦截的放参数类型
        Signature sig = pjp.getSignature();
        MethodSignature msig = null;
        if (!(sig instanceof MethodSignature)) {
            throw new IllegalArgumentException("该注解只能用于方法");
        }
        msig = (MethodSignature) sig;
        Class[] parameterTypes = msig.getMethod().getParameterTypes();

        Object object = null;

        Method method = null;
        try {
            method = target.getClass().getMethod(methodName, parameterTypes);
        } catch (NoSuchMethodException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (SecurityException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        if (null != method) {
            // 判断是否包含自定义的注解，说明一下这里的SystemLog就是我自己自定义的注解
            if (method.isAnnotationPresent(SystemLog.class)) {
                SystemLog systemlog = method.getAnnotation(SystemLog.class);
                log.setModule(systemlog.module());
                log.setMethod(systemlog.methods());
                log.setLoginIp(getIp(request));
                log.setActionUrl(request.getRequestURI());

                try {
                    object = pjp.proceed();
                    log.setDescription("执行成功");
                    log.setState((short) 1);
                } catch (Throwable e) {
                    // TODO Auto-generated catch block
                    log.setDescription("执行失败");
                    log.setState((short)-1);
                }
            } else {//没有包含注解
                object = pjp.proceed();
                log.setDescription("此操作不包含注解");
                log.setState((short)0);
            }
        } else { //不需要拦截直接执行
            object = pjp.proceed();
            log.setDescription("不需要拦截直接执行");
            log.setState((short)0);
        }
        return object;*/
    }

    /**
     * 获取ip地址
     * @param request
     * @return
     */
    private String getIp(HttpServletRequest request){
        if (request.getHeader("x-forwarded-for") == null) {
            return request.getRemoteAddr();
        }
        return request.getHeader("x-forwarded-for");
    }

}