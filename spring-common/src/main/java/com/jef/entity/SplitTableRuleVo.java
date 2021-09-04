package com.jef.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Jef
 * @date 2020/8/27
 */
public class SplitTableRuleVo implements Serializable {

    private static final long serialVersionUID = -5617497144502291969L;
    private String id;

    //分表表名
    private String tableName;

    //是否分表 0否 1是
    private int isShopID;

    //创建时间
    private Date createTime;

    // 店铺ID
    private Long shopID;

    //数据库名称（格式：数据库A. 或者 空字符串）
    private String dataBaseName;

    //分表后缀
    private String sufSName;

    //多张分表
    private String[] tableNameArr;

    //实际表名
    private String actualTable;


    public SplitTableRuleVo(){}

    public SplitTableRuleVo(String tableName,int isShopID,Long shopID,String sufSName,String dataBaseName){
        this.tableName = tableName;
        this.isShopID = isShopID;
        this.shopID = shopID;
        this.dataBaseName = dataBaseName;
        this.sufSName = sufSName;
    }


    public SplitTableRuleVo(Long shopID,String[] tableNameArr){
        this.shopID = shopID;
        this.tableNameArr = tableNameArr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public int getIsShopID() {
        return isShopID;
    }

    public void setIsShopID(int isShopID) {
        this.isShopID = isShopID;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getShopID() {
        return shopID;
    }

    public void setShopID(Long shopID) {
        this.shopID = shopID;
    }

    public String getSufSName() {
        return sufSName;
    }

    public void setSufSName(String sufSName) {
        this.sufSName = sufSName;
    }

    public String[] getTableNameArr() {
        return tableNameArr;
    }

    public void setTableNameArr(String[] tableNameArr) {
        this.tableNameArr = tableNameArr;
    }

    public String getDataBaseName() {
        return dataBaseName;
    }

    public void setDataBaseName(String dataBaseName) {
        this.dataBaseName = dataBaseName;
    }

    public String getActualTable() {
        return actualTable;
    }

    public void setActualTable(String actualTable) {
        this.actualTable = actualTable;
    }
}