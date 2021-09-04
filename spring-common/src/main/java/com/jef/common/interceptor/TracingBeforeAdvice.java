package com.jef.common.interceptor;

import com.jef.controller.PostController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @author Jef
 * @date: 2021/4/14 20:23
 */
public class TracingBeforeAdvice implements MethodBeforeAdvice {
    private static final Logger logger = LogManager.getLogger(PostController.class);
    @Override
    public void before(Method m, Object[] args, Object target) throws Throwable {
        if (args != null) {
            logger.warn(target.getClass().getName() + "." + m.getName() );
            logger.warn(target.getClass().getName());
            if (args.length > 0) {
                for(int i = 0;i < args.length; i++) {
                    logger.warn(args[i]);
                }
            }
        }
    }

}
