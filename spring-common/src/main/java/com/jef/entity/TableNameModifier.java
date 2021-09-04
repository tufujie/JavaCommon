package com.jef.entity;

import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlASTVisitorAdapter;

/**
 * @author Jef
 * @date 2020/8/27
 */
public class TableNameModifier extends MySqlASTVisitorAdapter {
    private String tableName;
    private String newTableName;

    public TableNameModifier(String tableName, String newTableName) {
        this.tableName = tableName.trim().toLowerCase();
        this.newTableName = newTableName.trim().toLowerCase();
    }

    //    @Override
//    public boolean visit(SQLSelectStatement astNode) {
//        astNode.getSelect().accept(this);
//        return false;
//    }
//    @Override
//    public boolean visit(SQLSelect x){
//        x.getQuery().accept(this);
//        return false;
//    }
//    @Override
//    public boolean visit(SQLExprTableSource x){
//        x.getExpr().accept(this);
//        return false;
//    }
    @Override
    public boolean visit(SQLIdentifierExpr x) {
        if(x.getName().toLowerCase().trim().equals(tableName)){
            x.setName(newTableName);
        }
        return false;
    }
}