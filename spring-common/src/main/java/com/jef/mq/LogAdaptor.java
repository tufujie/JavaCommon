package com.jef.mq;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 处理适配器
 * @author Jef
 * @date 2019/4/1
 */
@Component("logAdaptor")
public class LogAdaptor {
    private static final Logger logger = LogManager.getLogger(LogAdaptor.class);

    /** 
     * 处理日志
     * @author Jef
     * @date 2019/4/1
     * @param param 发送请求参数
     * @return
     */
    public void dealLog(Map param) throws Exception {
        String log = (String) param.get("log");
        logger.info("receive log=" + log);
    }

}