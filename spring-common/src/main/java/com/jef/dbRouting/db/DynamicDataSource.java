package com.jef.dbRouting.db;


import com.jef.dbRouting.DbContextHolder;
import com.jef.util.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * Spring的动态数据源的实现
 * @author Jef
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    public static final Logger logger = LogManager.getLogger(DynamicDataSource.class);

    @Override
    protected Object determineCurrentLookupKey() {
    	// 获取当前数据源，然后清除当前数据源
    	String dbKey = DbContextHolder.getDbKey();
    	DbContextHolder.clearDbKey();
    	return dbKey;
    }

}