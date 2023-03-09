package com.jef.common.interceptor;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlSchemaStatVisitor;
import com.alibaba.druid.stat.TableStat;
import com.alibaba.druid.util.JdbcConstants;
import com.jef.entity.SplitRule;
import com.jef.entity.SplitTableRuleVo;
import com.jef.entity.TableNameModifier;
import com.jef.util.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author Jef
 * @date 2020/8/27
 */
@Intercepts({ @Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class,Integer.class }) })
public class SplitTablePlugin implements Interceptor {


    private static final ThreadLocal<SplitTableRuleVo> localClass = new ThreadLocal<SplitTableRuleVo>();

    private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();

    private static final Field BOUNDSQL_SQL_FIELD;
    static {
        try {
            BOUNDSQL_SQL_FIELD = BoundSql.class.getDeclaredField("sql");
            BOUNDSQL_SQL_FIELD.setAccessible(true);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 分表规则
     * @param shopID
     */
    public static void setSplitRule(Long shopID){
        setSplitRule(shopID,null);
    }

    /**
     * 分表规则
     *
     * @param shopID
     * @param tableNameArr
     */
    public static void setSplitRule(Long shopID, String[] tableNameArr) {
        localClass.set(new SplitTableRuleVo(shopID, tableNameArr));
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (localClass.get() == null) {
            return invocation.proceed();
        }
        doSplitTable(invocation);
        // 传递给下一个拦截器处理
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // 当目标类是StatementHandler类型时，才包装目标类，否则直接返回目标本身,减少目标被代理的次数
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    private void doSplitTable(Invocation invocation) throws Exception {
        SplitTableRuleVo splitTableRuleVo = localClass.get();
        // 移除本地变量
        localClass.remove();
        //没有传店铺ID 需要分表的表名称，不需要解析sql
        if(StringUtils.isEmpty(splitTableRuleVo.getShopID())){
            return;
        }
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
//		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY,DEFAULT_REFLECTOR_FACTORY);
        BoundSql boundSql = statementHandler.getBoundSql();
        String originalSql =boundSql.getSql(); //(String) metaStatementHandler.getValue("delegate.boundSql.sql");
//		logger.info("分表前的SQL："+originalSql);

        String[] tableNameArr = splitTableRuleVo.getTableNameArr();
        Map<String,String> tableNameMap = new HashMap<String, String>();
        if (tableNameArr == null || splitTableRuleVo.getTableNameArr().length <= 0) {
            List<String> tableNameList = parserSql(originalSql);
            List<SplitTableRuleVo> splitTableRuleVoList = SplitRule.getSplitSql(splitTableRuleVo.getShopID(),tableNameList);
            for(SplitTableRuleVo splitTableRule : splitTableRuleVoList){
                tableNameMap.put(splitTableRule.getTableName(),(StringUtils.isEmpty(splitTableRule.getDataBaseName()) ? "" : splitTableRule.getDataBaseName()) + splitTableRule.getTableName()
                        + "_" + splitTableRule.getSName());
            }
        } else {
            for(String tableName : tableNameArr){
                if(StringUtils.isEmpty(tableName)){
                    continue;
                }
                SplitTableRuleVo splitTableRule = SplitRule.getSplitTableRuleVo(splitTableRuleVo.getShopID(), tableName);
                if(splitTableRule == null || splitTableRule.getIsShopID() == 0){
                    continue;
                }
                tableNameMap.put(tableName, (StringUtils.isEmpty(splitTableRule.getDataBaseName()) ? "" : splitTableRule.getDataBaseName()) + tableName + "_" + splitTableRule.getSName());
            }
        }
        if (tableNameMap.size() > 0) {
//			metaStatementHandler.setValue("delegate.boundSql.sql", originalSql);
            BOUNDSQL_SQL_FIELD.set(boundSql, replaceTableName(originalSql, tableNameMap));
        }
//		logger.info("分表后的SQL："+originalSql);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    /**
     * 解析sql，获取表名
     * @param sql
     * @return
     */
    public List<String> parserSql(String sql){
        String dbType = JdbcConstants.MYSQL;
        List<SQLStatement> stmtList = SQLUtils.parseStatements(sql, dbType);

        List<String> tableNameList = new ArrayList<String>();
        for (int i = 0; i < stmtList.size(); i++) {
            SQLStatement stmt = stmtList.get(i);
            MySqlSchemaStatVisitor visitor = new MySqlSchemaStatVisitor();
            stmt.accept(visitor);

            //获取表名称
            Map<TableStat.Name, TableStat> map = visitor.getTables();
            for(TableStat.Name name : map.keySet()){
                tableNameList.add(name.toString().toLowerCase());
            }
        }
        return tableNameList;
    }

    public static void main(String[] args){
        String sql = "update eee set s = 1;";
        Map<String,String> tableNameMap = new HashMap<String, String>();
        tableNameMap.put("eee","1112a");
        System.out.print(new SplitTablePlugin().replaceTableName(sql,tableNameMap));
    }

    /**
     * 替换表名
     * @param sql
     * @param tableNameMap
     * @return
     */
    public String replaceTableName(String sql ,Map<String,String> tableNameMap){
        boolean isMoreSql = false;
        if (sql.contains(";")) {
            isMoreSql = true;
        }
        StringBuilder out = new StringBuilder();
        MySqlOutputVisitor output = new MySqlOutputVisitor(out);
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        List<SQLStatement> sqlStatements = parser.parseStatementList();
        for (SQLStatement sqlStatement:sqlStatements) {
            TableNameModifier tnm;
            for(String tableName : tableNameMap.keySet()){
                tnm = new TableNameModifier(tableName,tableNameMap.get(tableName));
                sqlStatement.accept(tnm);
            }
            sqlStatement.accept(output);
            if (!isMoreSql && sqlStatements.size() > 1) {
                out.append(";");
            }
        }
        String resultSql = output.getAppender().toString();
        System.out.println("After SplitTable SQL=\n" + resultSql);
        return resultSql;
    }

}