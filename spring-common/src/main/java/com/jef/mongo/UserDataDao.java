package com.jef.mongo;

import com.jef.entity.UserDataVo;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author Jef
 * @date 2018/12/7 10:08
 */
@Repository
public class UserDataDao extends MongoDao <UserDataVo>{

    @Override
    protected Class getEntityClass() {
        return UserDataVo.class;
    }

    /**
     * 分页查询 对应mongodb操作中的 db.member.find().skip(10).limit(10);
     * @param start 用户分页查询的起始值
     * @param size 查询的数据数目
     *
     * @return 返回查询到的数据集合
     */
    public List<UserDataVo> queryPage(UserDataVo vo, Integer start, Integer size) {
        Query query = prepareCondition(vo);

        return this.getPage(query,(start-1)*size,size);
    }

    /**
     * 查询满足分页的记录总数
     * @return 返回满足条件的记录总数
     */
    public Long queryPageCount(UserDataVo vo){
        Query query = prepareCondition(vo);

        return this.getPageCount(query);
    }

    //准备条件
    public Query prepareCondition(UserDataVo vo){
        Query query = new Query();
//此处可以增加分页查询条件Criteria.然后query.addCriteria(criteria);
        if(!StringUtils.isEmpty(vo.getId())){
            Criteria level = Criteria.where("id").is(vo.getId());
            query.addCriteria(level);
        }
        if(!StringUtils.isEmpty(vo.getName())){
            Criteria level = Criteria.where("name").is(vo.getName());
            query.addCriteria(level);
        }
        if(!StringUtils.isEmpty(vo.getPhone())){
            Criteria year = Criteria.where("phone").is(vo.getPhone());
            query.addCriteria(year);
        }
        return query;
    }

    /**
     * 通过条件查询单个实体
     * @return
     */
    public UserDataVo queryOne(UserDataVo vo) {
        Query query = prepareCondition(vo);
        MongoTemplate mongoTemplate = getMongoTemplate();
        return (UserDataVo) mongoTemplate.findOne(query, getEntityClass());
    }


    /**
     * 通过条件查询
     * @return
     */
    public List<UserDataVo> queryList(UserDataVo vo) {
        Query query = prepareCondition(vo);
        return (List<UserDataVo>)queryList(query);
    }
}