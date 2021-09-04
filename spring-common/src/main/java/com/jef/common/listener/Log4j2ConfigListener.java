package com.jef.common.listener;

import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.web.Log4jServletContextListener;
import javax.servlet.ServletContextEvent;
import java.util.Enumeration;

/**
 * @author Jef
 * @date 2019/5/25
 */
public class Log4j2ConfigListener extends Log4jServletContextListener {

    private static final String KEY = "log4jConfigLocation";

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        String fileName = getContextParam(arg0);
        Configurator.initialize("Log4j2", fileName);
    }

    private String getContextParam(ServletContextEvent event) {
        Enumeration<String> names = event.getServletContext().getInitParameterNames();
        while (names.hasMoreElements()){
            String name = names.nextElement();
            String value = event.getServletContext().getInitParameter(name);
            if(name.trim().equals(KEY)){
                return value;
            }
        }
        return null;
    }
}