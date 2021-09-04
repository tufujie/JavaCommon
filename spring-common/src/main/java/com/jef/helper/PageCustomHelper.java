package com.jef.helper;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlOutputVisitor;
import com.jef.entity.Page;
import com.jef.entity.SplitRule;
import com.jef.entity.SplitTableRuleVo;
import com.jef.entity.TableNameModifier;
import com.jef.util.StringUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ResultMap;
import org.apache.ibatis.mapping.ResultMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Title:Mybatis - 通用分页拦截器
 * @author Leo  date:Dec 18, 2015 5:25:00 PM
 * @version PropertyCloud V1.0
 */
@Intercepts(@Signature(type = Executor.class, method = "query",
        args = { MappedStatement.class, Object.class,
                RowBounds.class, ResultHandler.class }))
@SuppressWarnings("unchecked")
public class PageCustomHelper implements Interceptor {
    private static final ThreadLocal<Page> localPage = new ThreadLocal<Page>();
    private static String countSuffix = "_COUNT";
    private static final List<ResultMapping> EMPTY_RESULTMAPPING = new ArrayList<ResultMapping>(0);
    private static String dialect = "mysql";
    // RowBounds参数offset作为PageNum使用 - 默认不使用
    private static boolean offsetAsPageNum = false;
    // RowBounds是否进行count查询 - 默认不查询
    private static boolean rowBoundsWithCount = false;
    //
    private static boolean isNewSearch = true;
    private boolean replaceSql = true;

    /**
     * 开始分页
     *
     * @param pageNum
     * @param pageSize
     * @author Leo
     * @date:Dec 18, 2015 5:25:38 PM
     */
    public static void startPage(int pageNum, int pageSize) {
        startPage(pageNum, pageSize, true);
    }


    public static void startPage(int pageNum, int pageSize, boolean count) {
        if (pageSize == -1) {
            count = false;
        }
        if (pageSize == 0) {
            count = true;
        }
        localPage.set(new Page(pageNum, pageSize, count));
    }

    public static void startPage(Long shopID, String[] tableName) {
        localPage.set(new Page(-1, -1, false, shopID, tableName));
    }

    public static void startPage(int pageNum, int pageSize, boolean count, Long shopID, String[] tableName) {
        if (pageSize == -1) {
            count = false;
        }
        if (pageSize == 0) {
            count = true;
        }
        localPage.set(new Page(pageNum, pageSize, count, shopID, tableName));
    }

    public static void startPage(int pageNum, int pageSize, Long shopID, String[] tableName) {
        startPage(pageNum, pageSize, true, shopID, tableName);
    }

    public static void clearPage() {
        localPage.remove();
    }

    /**
     * 执行分页
     *
     * @author Leo
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        if (isNewSearch) {
            final Object[] args = invocation.getArgs();
            RowBounds rowBounds = (RowBounds) args[2];
            if (localPage.get() == null && rowBounds == RowBounds.DEFAULT) {
                return invocation.proceed();
            } else {
                // 分页信息
                Page page = localPage.get();
                // 移除本地变量
                localPage.remove();
                if (page == null) {
                    if (offsetAsPageNum) {
                        page = new Page(rowBounds.getOffset(), rowBounds.getLimit(), rowBoundsWithCount);
                    } else {
                        throw new RuntimeException("暂不支持此方式");
                        // page = new Page(rowBounds, rowBoundsWithCount);
                    }
                }
                // 忽略RowBounds-否则会进行Mybatis自带的内存分页
                MappedStatement ms = (MappedStatement) args[0];
                Object parameter = args[1];
                BoundSql boundSql = ms.getBoundSql(parameter);
                ResultHandler resultHandler = (ResultHandler) args[3];
                Executor executor = (Executor) invocation.getTarget();

                //疑难杂症－MyBatis一级缓存引起的分页插件失效
                //参考https://www.jianshu.com/p/4e24fafbc0d4   关键在与CacheKey
                if (!ms.isFlushCacheRequired()) {
                    Class<?> clazz = ms.getClass();
                    Field flushLocalCache = clazz.getDeclaredField("flushCacheRequired");
                    flushLocalCache.setAccessible(true);
                    flushLocalCache.set(ms, true);
                }

                CacheKey cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
                if (page != null && page.isCount()) {
//                String countMsId = ms.getId() + countSuffix;
                    MappedStatement countMs = newCountMappedStatement(ms);
                    Field additionalParametersField = BoundSql.class.getDeclaredField("additionalParameters");
                    additionalParametersField.setAccessible(true);
                    Map<String, Object> additionalParameters = (Map<String, Object>) additionalParametersField.get(boundSql);
                    //调用方言获取 count sql
                    //创建 count 查询的缓存 key
                    CacheKey countKey = executor.createCacheKey(countMs, parameter, RowBounds.DEFAULT, boundSql);
                    String countSql = getCountMySQLSql(countMs, boundSql, page);
                    //countKey.update(countSql);
                    BoundSql countBoundSql = new BoundSql(countMs.getConfiguration(), countSql, boundSql.getParameterMappings(), parameter);
                    //当使用动态 SQL 时，可能会产生临时的参数，这些参数需要手动设置到新的 BoundSql 中
                    for (String key : additionalParameters.keySet()) {
                        countBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
                    }
                    //执行 count 查询
                    Object countResultList = executor.query(countMs, parameter, RowBounds.DEFAULT, resultHandler, countKey, countBoundSql);
                    Long count = (Long) ((List) countResultList).get(0);

                    page.setTotal(count);
                    if (page.getPageSize() > 0) {
                        String pageSql = getPageSql(boundSql.getSql(), page);
                        //使用原有的ms ，同一个事务中如果在执行这个语句，仍然是带有分页的查询
                        //countKey.update(countSql);
                        BoundSql pageBoundSql = new BoundSql(ms.getConfiguration(), pageSql, boundSql.getParameterMappings(), parameter);
                        CacheKey pageKey = cacheKey;
                        //当使用动态 SQL 时，可能会产生临时的参数，这些参数需要手动设置到新的 BoundSql 中
                        for (String key : additionalParameters.keySet()) {
                            pageBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
                        }
                        Object result = executor.query(ms, parameter, RowBounds.DEFAULT, resultHandler, pageKey, pageBoundSql);
                        page.addAll((List) result);
                    }
                    return page;
                } else {
                    //不进行求count处理
                    //调用方言获取 count sql
                    //pageSize==-1后者
                    BoundSql pageBoundSql;
                    Field additionalParametersField = BoundSql.class.getDeclaredField("additionalParameters");
                    additionalParametersField.setAccessible(true);
                    Map<String, Object> additionalParameters = (Map<String, Object>) additionalParametersField.get(boundSql);
                    if (page.getPageSize() > 0) {
                        //使用原有的ms
                        page.setTotal(page.getPageSize());
                    }
                    String pageSql = getPageSql(boundSql.getSql(), page);
                    pageBoundSql = new BoundSql(ms.getConfiguration(), pageSql, boundSql.getParameterMappings(), parameter);
//                        //当使用动态 SQL 时，可能会产生临时的参数，这些参数需要手动设置到新的 BoundSql 中
                    for (String key : additionalParameters.keySet()) {
                        pageBoundSql.setAdditionalParameter(key, additionalParameters.get(key));
                    }
                    Object result = executor.query(ms, parameter, RowBounds.DEFAULT, resultHandler, cacheKey, pageBoundSql);
                    // 得到处理结果
                    page.addAll((List) result);
                    page.setTotal(((List) result).size());
                    return page;

                }
            }

        } else {
            final Object[] args = invocation.getArgs();
            RowBounds rowBounds = (RowBounds) args[2];
            if (localPage.get() == null && rowBounds == RowBounds.DEFAULT) {
                return invocation.proceed();
            } else {
                // 忽略RowBounds-否则会进行Mybatis自带的内存分页
                args[2] = RowBounds.DEFAULT;
                MappedStatement ms = (MappedStatement) args[0];
                Object parameterObject = args[1];
                BoundSql boundSql = ms.getBoundSql(parameterObject);

                // 分页信息
                Page page = localPage.get();
                // 移除本地变量
                localPage.remove();

                if (page == null) {
                    if (offsetAsPageNum) {
                        page = new Page(rowBounds.getOffset(), rowBounds.getLimit(), rowBoundsWithCount);
                    } else {
                        throw new RuntimeException("暂不支持此方式");
                        // page = new Page(rowBounds, rowBoundsWithCount);
                    }
                }
                MappedStatement qs = newCountMappedStatement(ms, boundSql);
                // 将参数中的MappedStatement替换为新的qs，防止并发异常
                args[0] = qs;
                MetaObject msObject = SystemMetaObject.forObject(qs);
                String sql = (String) msObject.getValue("sqlSource.boundSql.sql");
                // 简单的通过total的值来判断是否进行count查询

                if (page.isCount()) {
                    // 求count - 重写sql
                    msObject.setValue("sqlSource.boundSql.sql", getCountSql(sql, page));
                    // 查询总数
                    Object result = invocation.proceed();
                    Long count = (Long) ((List) result).get(0);
                    page.setTotal(count);
                    // 分页sql - 重写sql
                    msObject.setValue("sqlSource.boundSql.sql", getPageSql(sql, page));
                    // 恢复类型
                    msObject.setValue("resultMaps", ms.getResultMaps());
                    // 执行分页查询
                    result = invocation.proceed();
                    // 得到处理结果
                    page.addAll((List) result);
                    // 返回结果25 30
                    return page;
                } else {
                    // 求count - 重写sql
                    msObject.setValue("sqlSource.boundSql.sql", getCountSql(sql, page));
                    // 查询总数
                    Object result = invocation.proceed();
                    int totalCount = (Integer) ((List) result).get(0);
                    // 分页sql - 重写sql
                    msObject.setValue("sqlSource.boundSql.sql", getPageSql(sql, page));
                    // 恢复类型
                    msObject.setValue("resultMaps", ms.getResultMaps());
                    // 执行分页查询
                    result = invocation.proceed();
                    //封装总条数
                    page.setTotal(totalCount);
                    // 得到处理结果
                    page.addAll((List) result);
                    // 返回结果
                    return page;

                }
            }
        }
    }

    private MappedStatement newCountMappedStatement(MappedStatement ms, BoundSql boundSql) {
        return newMappedStatement(ms, ms.getId() + countSuffix, new BoundSqlSqlSource(boundSql), true);
    }

    private static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        @Override
        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

    /**
     * 新建count查询的MappedStatement
     *
     * @param ms
     * @param newMsId
     * @return
     */
    public MappedStatement newMappedStatement(MappedStatement ms, String newMsId, SqlSource sqlSource, boolean isNewResult) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(), newMsId, sqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null && ms.getKeyProperties().length != 0) {
            StringBuilder keyProperties = new StringBuilder();
            for (String keyProperty : ms.getKeyProperties()) {
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length() - 1, keyProperties.length());
            builder.keyProperty(keyProperties.toString());
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());

        if (isNewResult) {
            //count查询返回值int
            List<ResultMap> resultMaps = new ArrayList<ResultMap>();
            ResultMap resultMap = new ResultMap.Builder(ms.getConfiguration(), ms.getId(), Long.class, EMPTY_RESULTMAPPING).build();
            resultMaps.add(resultMap);
            builder.resultMaps(resultMaps);
        } else {
            builder.resultMaps(ms.getResultMaps());
        }
        builder.resultSetType(ms.getResultSetType());
        builder.cache(ms.getCache());
        builder.flushCacheRequired(ms.isFlushCacheRequired());
        builder.useCache(ms.isUseCache());

        return builder.build();


    }

    /**
     * 新建count查询的MappedStatement
     *
     * @param ms
     * @return
     */
    public MappedStatement newCountMappedStatement(MappedStatement ms) {
        return newMappedStatement(ms, ms.getId() + countSuffix, ms.getSqlSource(), true);
    }

    private String getCountMySQLSql(MappedStatement countMs, BoundSql boundSql, Page page) {
        String countColumn = page.getCountColumn();
        if (StringUtils.isNotEmpty(countColumn)) {
            return getSimpleCountSql(boundSql.getSql(), countColumn, page);
        }
        return getCountSql(boundSql.getSql(), page);
    }

    /**
     * 获取普通的Count-sql
     *
     * @param sql 原查询sql
     * @return 返回count查询sql
     */
    public String getSimpleCountSql(String sql, String name, Page page) {
        sql = replaceSql(sql, page);
        StringBuilder stringBuilder = new StringBuilder(sql.length() + 40);
        stringBuilder.append("select count(");
        stringBuilder.append(name);
        stringBuilder.append(") as pageHelperCount from (");
        stringBuilder.append(sql);
        stringBuilder.append(") tmp_count");
        return stringBuilder.toString();
    }

    /**
     * sql替换
     *
     * @param sql
     * @param page
     * @return
     */
    private String replaceSql(String sql, Page page) {
        if (!replaceSql) {
            return sql;
        }
        if (page.getShopID() != null && page.getTableName().length > 0) {
            String[] tableNameArr = page.getTableName();
            Map<String, String> tableNameMap = new HashMap<String, String>();
            for (String tableName : tableNameArr) {
                if (StringUtils.isEmpty(tableName)) {
                    continue;
                }
                SplitTableRuleVo splitTableRule = SplitRule.getSplitTableRuleVo(page.getShopID(), tableName);
                if (splitTableRule == null || splitTableRule.getIsShopID() == 0) {
                    continue;
                }
                tableNameMap.put(tableName, (StringUtils.isEmpty(splitTableRule.getDataBaseName()) ? "" : splitTableRule.getDataBaseName()) + tableName + "_" + splitTableRule.getSufSName());
            }
            if (tableNameMap.size() > 0) {
                sql = repalceTableName(sql, tableNameMap);
            }
        }
        return sql;
    }

    /**
     * 获取总数sql - 如果要支持其他数据库，修改这里就可以
     *
     * @param sql
     * @return
     * @author Leo
     * @date:Dec 18, 2015 5:26:19 PM
     */
    private String getCountSql(String sql, Page page) {
        sql = replaceSql(sql, page);
        String lowerSql = sql.toLowerCase();
        //包含group by 和 union 采用原来的方式
        if (lowerSql.contains("group by") || lowerSql.contains("group  by") || lowerSql.contains("union")) {
            return "select count(0) as pageHelperCount from (" + sql + ") tmp_count";
        }
        int firstSelect = lowerSql.indexOf("select");
        int firstFrom = lowerSql.indexOf("from");
        if (firstSelect >= 0 && firstFrom > 0 && firstSelect < firstFrom) {
            //如果包含有内置函数from_* 采用以前的方式
            String afterFromSql = lowerSql.substring(firstFrom + 4, firstFrom + 5)
                    .replace("\n", "")
                    .replace("\t", "")
                    .trim();
            if (!"".equals(afterFromSql)) {
                return "select count(0) as pageHelperCount from (" + sql + ") tmp_count";
            }
            String column = lowerSql.substring(firstSelect + 6, firstFrom);
            if (column.contains("?") || column.contains("distinct")) {//查询列中也用到了参数，采用以前的方式统计总数
                return "select count(0) as pageHelperCount from (" + sql + ") tmp_count";
            }
            if (!column.contains("select")) {
                //查询去除最后sql 中的order by （排序）
                int lastOrder = lowerSql.lastIndexOf("order by");
                if (lastOrder > 0) {
                    String orderSql = lowerSql.substring(lastOrder);
                    if (!orderSql.contains("select") && !orderSql.contains("where")) {
                        sql = sql.substring(0, lastOrder);
                    }
                }
                return "select count(0) as pageHelperCount " + sql.substring(firstFrom);
            }
        }
        return "select count(0) as pageHelperCount from (" + sql + ") tmp_count";
    }

    /**
     * 获取分页sql - 如果要支持其他数据库，修改这里就可以
     *
     * @param sql
     * @param page
     * @return
     * @author Leo
     * @date:Dec 18, 2015 5:26:31 PM
     */
    private String getPageSql(String sql, Page page) {
        StringBuilder pageSql = new StringBuilder(200);
        sql = replaceSql(sql, page);
        if ("mysql".equalsIgnoreCase(dialect)) {
            pageSql.append(sql);
            //取消是否有limit的判断，便于抛出异常分析问题  page.getTotal() > Page.DFAULT_COUNT && sql.toLowerCase().indexOf("limit ")==-1
            if (page.getTotal() >= Page.DFAULT_COUNT) {
                pageSql.append(" limit " + page.getStartRow() + "," + page.getPageSize());
            }
        } else if ("hsqldb".equalsIgnoreCase(dialect)) {
            pageSql.append(sql);
            if (page.getTotal() > Page.DFAULT_COUNT) {
                pageSql.append(" LIMIT " + page.getPageSize() + " OFFSET " + page.getStartRow());
            }
        } else if ("oracle".equalsIgnoreCase(dialect)) {
            if (page.getTotal() > Page.DFAULT_COUNT) {
                pageSql.append("select * from ( select temp.*, rownum row_id from ( ");
                pageSql.append(sql);
                pageSql.append(" ) temp where rownum <= ").append(page.getEndRow());
                pageSql.append(") where row_id > ").append(page.getStartRow());
            } else {
                pageSql.append(sql);
            }
        } else {
            throw new IllegalStateException("unknown sql dialect: " + dialect);
        }
        return pageSql.toString();
    }

    /**
     * 只拦截Executor
     *
     * @param target
     * @return
     */
    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties p) {
        String dialectStr = p.getProperty("dialect");
        if (StringUtils.isNotEmpty(dialectStr)) {
            dialect = dialectStr;
        }
        // offset作为PageNum使用
        String offset = p.getProperty("offsetAsPageNum");
        if ("TRUE".equalsIgnoreCase(offset)) {
            offsetAsPageNum = true;
        }
        // RowBounds方式是否做count查询
        String withcount = p.getProperty("rowBoundsWithCount");
        if ("TRUE".equalsIgnoreCase(withcount)) {
            rowBoundsWithCount = true;
        }
        String newSearch = p.getProperty("newSearch");
        if ("FALSE".equalsIgnoreCase(newSearch)) {
            isNewSearch = false;
        }
        String replaceSqlStr = p.getProperty("replaceSql");
        if ("FALSE".equalsIgnoreCase(replaceSqlStr)) {
            replaceSql = false;
        }
    }

    /**
     * 替换表名
     *
     * @param sql
     * @param tableNameMap
     * @return
     */
    public String repalceTableName(String sql, Map<String, String> tableNameMap) {
        boolean isMoreSql = false;
        if (sql.contains(";")) {
            isMoreSql = true;
        }
        StringBuilder out = new StringBuilder();
        MySqlOutputVisitor output = new MySqlOutputVisitor(out);
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        List<SQLStatement> sqlStatements = parser.parseStatementList();
        for (SQLStatement sqlStatement : sqlStatements) {
            TableNameModifier tnm = null;
            for (String tableName : tableNameMap.keySet()) {
                tnm = new TableNameModifier(tableName, tableNameMap.get(tableName));
                sqlStatement.accept(tnm);
            }
            sqlStatement.accept(output);
            if (!isMoreSql && sqlStatements.size() > 1) {
                out.append(";");
            }
        }
        return output.getAppender().toString();
    }
}