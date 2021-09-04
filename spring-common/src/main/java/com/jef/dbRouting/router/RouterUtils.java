package com.jef.dbRouting.router;

import com.jef.dbRouting.security.Base64Binrary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RouterUtils {
    private static final Logger logger = LogManager.getLogger(RouterUtils.class);
    /**
     * 默认编码
     */
    private final static String encode = "utf-8";
    /**
     * 最大资源数
     */
    private final static int resourceMax = 10000;

    /**
     * 获取hashCode
     *
     * @param routeValue
     * @return
     */
    public static int getHashCodeBase64(String routeValue) {
        int hashCode = 0;
        try {
            String pinBase64 = Base64Binrary.encodeBase64Binrary(routeValue.getBytes(encode));
            hashCode = Math.abs(pinBase64.hashCode());
        } catch (Exception e) {
            logger.error("hashCode 失败", e);
        }
        return hashCode;
    }

    /**
     * 获取资源码
     *
     * @param routeValue
     * @return
     */
    public static int getResourceCode(String routeValue) {
        int hashCode = RouterUtils.getHashCodeBase64(routeValue);
        int resourceCode = hashCode % resourceMax;
        return resourceCode;
    }


    public static void main(String args[]) {
        String payid = "140331160123935469773";
        String resource = payid.substring(payid.length() - 4);
        int routeFieldInt = Integer.valueOf(resource);
        int mode = 6 * 200;
        int dbIndex = routeFieldInt % mode / 200;
        int tbIndex = routeFieldInt % 200;
        System.out.println(dbIndex + "-->" + tbIndex);
    }
}
