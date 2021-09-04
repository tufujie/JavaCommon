package com.jef.mongo;

import com.jef.common.context.SpringContextHolder;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Map;

/**
 * @author Jef
 * @date 2018/12/7 10:05
 */
public abstract class MongoDao <T> {
    private final Logger logger = LogManager.getLogger(MongoDao.class);

    public MongoTemplate getMongoTemplate() {
        return SpringContextHolder.getBean("mongoTemplate");
    }

    /**
     * 保存对象
     * @param obj
     */
    public void save(Object obj) {
        MongoTemplate mongoTemplate = getMongoTemplate();
        mongoTemplate.save(obj);
    }

    /**
     * 查找对象
     * @param id
     * @return
     */
    public Object findOne(String id) {
        MongoTemplate mongoTemplate = getMongoTemplate();
        Query query = new Query();
        Criteria criteria = Criteria.where("_id").is(id);
        query.addCriteria(criteria);
        return mongoTemplate.findOne(query, getEntityClass());
    }

    /**
     * 通过条件查询单个实体
     * @param query
     * @return
     */
    public Object queryOne(Query query) {
        MongoTemplate mongoTemplate = getMongoTemplate();

//	logger.info("[Mongo Dao ]queryOne:" + query);
        return mongoTemplate.findOne(query, getEntityClass());
    }

    /**
     * 通过条件查询单个实体
     * @param query
     * @return
     */
    public List<T> queryList(Query query) {
//logger.info("[Mongo Dao ]queryList:" + query);
        MongoTemplate mongoTemplate = getMongoTemplate();

        return mongoTemplate.find(query, getEntityClass());
    }

    /**
     * 通过条件进行分页查询
     * @param query
     * @param start
     * @param size
     * @return
     */
    public List<T> getPage(Query query, int start, int size) {
        MongoTemplate mongoTemplate = getMongoTemplate();

        query.skip(start);
        query.limit(size);
//logger.info("[Mongo Dao ]queryPage:" + query + "(" + start +"," + size +")");
        List<T> lists = mongoTemplate.find(query, getEntityClass());
        return lists;
    }

    /**
     * 根据条件查询库中符合记录的总数,为分页查询服务 *
     * @param query 查询条件
     * @return 满足条件的记录总数
     */
    public Long getPageCount(Query query){
// log.info("[Mongo Dao ]queryPageCount:" + query);
        return getMongoTemplate().count(query, this.getEntityClass());
    }


    /**
     * 删除对象
     * @param ids
     */
    public void delete(String[] ids) {
        if (ids == null || ids.length == 0) {
            return;
        }

        MongoTemplate mongoTemplate = getMongoTemplate();

        for (String id : ids) {
            Query query = new Query(Criteria.where("_id").is(id));
            mongoTemplate.remove(query, getEntityClass());
        }
    }

    /**
     * 通过条件查询单个实体
     * @param id
     */
    public void deleteById(String id) {
        Criteria criteria = Criteria.where("_id").in(id);
        if (null != criteria) {
            Query query = new Query(criteria);
//logger.info("[Mongo Dao ]deleteById:" + query);
            if (null != query && queryOne(query) != null) {
                delete(query);
            }
        }
    }

    /**
     * 删除对象操作
     * @param query
     */
    public void delete(Query query) {
//logger.info("[Mongo Dao ]delete:" + t);

        MongoTemplate mongoTemplate = getMongoTemplate();
        mongoTemplate.remove(query, getEntityClass());
    }

/**
 * 说明:Mongodb的修改操作大致有3中.
 mongoTemplate.updateFirst操作、mongoTemplate.updateMulti操作、this.mongoTemplate.upsert操作.
 分别表示修改第一条、修改符合条件的所有、修改时如果不存在则添加.
 */

    /**
     * 更新满足条件的第一个记录
     * @param query
     * @param update
     */
    public void updateFirst(Query query, Update update) {
        MongoTemplate mongoTemplate = getMongoTemplate();
//logger.info("[Mongo Dao ]updateFirst:query(" + query + "),update(" + update + ")");
        mongoTemplate.updateFirst(query, update, getEntityClass());
    }

    /**
     * 更新满足条件的所有记录
     * @param query
     * @param update
     */
    public void updateMulti(Query query, Update update) {
        MongoTemplate mongoTemplate = getMongoTemplate();
//logger.info("[Mongo Dao ]updateMulti:query(" + query + "),update(" + update + ")");
        mongoTemplate.updateMulti(query, update, getEntityClass());
    }

    /**
     * 查找更新,如果没有找到符合的记录,则将更新的记录插入库中
     * @param query
     * @param update
     */
    public void updateInser(Query query, Update update) {
        MongoTemplate mongoTemplate = getMongoTemplate();
//logger.info("[Mongo Dao ]updateInser:query(" + query + "),update(" + update + ")");

        mongoTemplate.upsert(query, update, getEntityClass());
    }

    /**
     * 计算某个字段是和
     * @param collection
     * @param filedName
     * @return
     */
    public double sumField(String collection,String filedName,Criteria criteria) {
        double total = 0l;
        String reduce = "function(doc, aggr){" +
                " aggr.total += parseFloat((Math.round((doc." + filedName + ")*100)/100).toFixed(2));" +
                " }";
        Query query = new Query();
        if(criteria!=null){
            query.addCriteria(criteria);
        }
        DBObject result = getMongoTemplate().getCollection(collection).group(null,
                query.getQueryObject(),
                new BasicDBObject("total", total),
                reduce);

        Map<String,BasicDBObject> map = result.toMap();
        if(map.size() > 0){
            BasicDBObject bdbo = map.get("0");
            if(bdbo != null && bdbo.get("total") != null)
                total = bdbo.getDouble("total");
        }
        return total;
    }

    /**
     * 钩子方法,由子类实现返回反射对象的类型
     *
     * @author <a href='mailto:dennisit@163.com'>Cn.pudp(En.dennisit)</a> Copy Right since 2013-10-13 下午03:21:48
     *
     * @return
     */
    protected abstract Class<T> getEntityClass();
}