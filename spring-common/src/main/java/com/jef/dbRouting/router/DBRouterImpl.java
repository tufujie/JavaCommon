package com.jef.dbRouting.router;

import com.jef.controller.LoginController;
import com.jef.dbRouting.DBRouter;
import com.jef.dbRouting.DbContextHolder;
import com.jef.dbRouting.annotation.RouterConstants;
import com.jef.dbRouting.bean.RouterSet;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 根据指定变量动态切 库和表
 * @autohr Jef
 */
public class DBRouterImpl implements DBRouter {
    private static Logger logger = LogManager.getLogger(DBRouterImpl.class);

    /**
     * 配置列表
     */
    private List<RouterSet> routerSetList;

    @Override
    public String doRoute(Integer routeFieldInt) {
        if (routeFieldInt == null) {
            throw new IllegalArgumentException("dbsCount and tablesCount must be both positive!");
        }
        String dbKey = getDbKey(routerSetList, routeFieldInt);
        return dbKey;
    }

    @Override
    public String doRouteByResource(String resource) {
        if (StringUtils.isEmpty(resource)) {
            throw new IllegalArgumentException("dbsCount and tablesCount must be both positive!");
        }
        Integer routeFieldInt = RouterUtils.getResourceCode(resource);
        return doRoute(routeFieldInt);
    }


    /**
     * 根据数据字段来判断属于哪个段的规则,获得数据库key
     */
    private String getDbKey(List<RouterSet> routerSets, int routeFieldInt) {
        RouterSet routerSet = null;
        if (routerSets == null || routerSets.size() <= 0) {
            throw new IllegalArgumentException("dbsCount and tablesCount must be both positive!");
        }
        String dbKey = null;
        for (RouterSet item : routerSets) {
            if (item.getRuleType() == RouterSet.RULE_TYPE_STR) {
                routerSet = item;
                if (routerSet.getDbKeyArray() != null && routerSet.getDbNumber() != 0) {
                    long dbIndex = 0;
                    long tbIndex = 0;
                    // 默认按照分库进行计算
                    long mode = routerSet.getDbNumber();
                    // 如果是按照分库分表的话，计算
                    if (item.getRouteType() == RouterSet.ROUTER_TYPE_DBANDTABLE && item.getTableNumber() != 0) {
                        mode = routerSet.getDbNumber() * item.getTableNumber();
                        dbIndex = routeFieldInt % mode / item.getTableNumber();
                        tbIndex = routeFieldInt % item.getTableNumber();
                        String tableIndex = getFormateTableIndex(item.getTableIndexStyle(), tbIndex);
                        DbContextHolder.setTableIndex(tableIndex);
                    } else if (item.getRouteType() == RouterSet.ROUTER_TYPE_DB) {
                        mode = routerSet.getDbNumber();
                        dbIndex = routeFieldInt % mode;
                    } else if (item.getRouteType() == RouterSet.ROUTER_TYPE_TABLE) {
                        tbIndex = routeFieldInt % item.getTableNumber();
                        String tableIndex = getFormateTableIndex(item.getTableIndexStyle(), tbIndex);
                        DbContextHolder.setTableIndex(tableIndex);
                    }
                    dbKey = routerSet.getDbKeyArray().get(Long.valueOf(dbIndex).intValue());
                    logger.debug("获得最终连接结果 routeFieldInt:{}------->dbKey:{},tableIndex:{},", routeFieldInt, dbKey, tbIndex);
                    DbContextHolder.setDbKey(dbKey);
                }
                break;
            }
        }
        return dbKey;
    }


    /**
     * 此方法是将例如+++0000根式的字符串替换成传参数字例如11 变成+++0011
     * 这种方式支持分表数量为0000到9999，最多可分1万张表
     * 根据分表数量进行设计即可
     */
    private static String getFormateTableIndex(String style, long tbIndex) {
        String tableIndex = null;
        DecimalFormat df = new DecimalFormat();
        if (StringUtils.isEmpty(style)) {
            // 在格式后添加诸如单位等字符
            style = RouterConstants.ROUTER_TABLE_SUFFIX_DEFAULT;
        }
        df.applyPattern(style);
        tableIndex = df.format(tbIndex);
        return tableIndex;
    }

    public List<RouterSet> getRouterSetList() {
        return routerSetList;
    }

    public void setRouterSetList(List<RouterSet> routerSetList) {
        this.routerSetList = routerSetList;
    }
}
