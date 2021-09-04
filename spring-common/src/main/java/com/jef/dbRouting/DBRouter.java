package com.jef.dbRouting;

/**
 * DB路由接口  DB路由器接口，通过调用该接口来自动判断数据位于哪个服务器
 * @author Jef
 */
public interface DBRouter {
	/**
	 * 进行路由
	 * @param fieldId
	 * @return
	 * @throws
	 */
    String doRoute(Integer routeFieldInt);

    /**
     * 通过资源获取路由
     * @author Jef
     * @date 2021/4/4
     * @param resourceCode
     * @return java.lang.String
     */
    String doRouteByResource(String resource);
}
