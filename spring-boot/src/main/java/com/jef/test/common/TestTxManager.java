package com.jef.test.common;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TestTxManager {

    DataSourceTransactionManager txManager;
    TransactionStatus status;
    public TestTxManager(){}


    public TestTxManager(DataSourceTransactionManager manager){
        this.txManager = manager;
    }

    /**
     * 开启事务
     */
    public void begin(){
        status =  txManager.getTransaction(new DefaultTransactionDefinition());
    }
    /**
     * 提交事务
     */
    public void commit(){
        if(status!=null) {
            txManager.commit(status);
        }
    }
    /**
     * 回滚事务
     */
    public void rollback(){
        if(status!=null){
            txManager.rollback(status);
        }
    }
}