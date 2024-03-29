package com.jef.service;

//import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface IWorkBusinessService {

    /**
     * 直连数据库处理：增删改查
     *
     * @author Jef
     * @date 2022/1/15
     */
    void getUserForHandler();

    /**
     * 场景：客户提供excel用于初始化数据
     * 根据提供的excel初始化数据，即插入数据
     *
     * @param fileUrl     文件地址
     * @param tableName   表名
     * @param columnNames 字段名
     * @param ecID        企业标识ID
     * @param mapParams   中文映射为数据库存储内容
     * @author Jef
     * @date 2022/1/15
     */
    void getExcelInsertSQL(String fileUrl, String tableName, String columnNames, String ecID, Map<String, String> mapParams) throws Exception;

    /**
     * 场景：客户提供excel用于更新数据
     * 数据从生产库捞取后（包括表结构和所需数据）后直连数据库处理：读取excel后增删改查
     *
     * @author Jef
     * @date 2022/1/15
     */
    void getUpdateContractTypeSQL() throws Exception;

    /**
     * 场景：客户提供excel用于更新数据
     * 数据从生产库捞取后（包括表结构和所需数据）后直连数据库处理：读取excel后增删改查
     *
     * @author Jef
     * @date 2022/1/15
     */
    void testGetUpdateRoomBuildingTypeSQL() throws Exception;

    /**
     * 场景：客户提供excel用于更新数据
     * 数据从生产库捞取后（包括表结构和所需数据）后直连数据库处理：读取excel后增删改查
     *
     * @author Jef
     * @date 2022/1/15
     */
    void testGetUpdateDemanPoolSQL() throws Exception;

    /**
     * 解析银行分行数据
     *
     * @param fileUrl
     * @param tableName
     * @param columnNames
     * @throws Exception
     */
    void getExcelInsertSQLOfBranchBank(String fileUrl, String tableName, String columnNames) throws Exception;

    /**
     * 将excel的数据形成json方便生成实体
     *
     * @param fileUrl
     * @throws Exception
     */
    void getExcelToJson(String fileUrl, int levelType) throws Exception;
}
