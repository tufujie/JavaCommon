package com.jef.controller;

import com.jef.common.utils.BasicJspUtil;
import com.jef.constant.BasicConstant;
import com.jef.entity.UserDataVo;
import com.jef.mongo.UserDataDao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 测试mongo的controller
 *
 * @author Jef
 * @date 2018/11/30 10:25
 */
@Controller
@RequestMapping(value = "/mongo")
public class MongoController {
    private static Logger logger = LogManager.getLogger(MongoController.class);

    @Autowired
    private UserDataDao userDataDao;

    @RequestMapping(value = "/mongoIntroduce")
    public ModelAndView introduce(ModelAndView mv) {
        mv.setViewName("mongo/mongoIntroduce");
        return mv;
    }

    /**
     * 添加mongo数据，如果id相同会进行修改
     */
    @ResponseBody
    @RequestMapping(value = "/addVo", method = RequestMethod.POST)
    public String addVo(@RequestBody UserDataVo user) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        user.setCreateDate(simpleDateFormat.format(new Date()));
        UserDataVo userDataVo = (UserDataVo) userDataDao.findOne(user.getId());
        if (userDataVo == null) {
            userDataDao.save(user);
        } else {
            // 具体的某些字段
            userDataVo.setName(user.getName());
            userDataDao.save(userDataVo);
        }

        return BasicConstant.SUCCESS;
    }

    /**
     * 获取mongo数据
     */
    @RequestMapping(value = "/getVo")
    public ModelAndView getVo(ModelAndView mv) throws Exception {
        UserDataVo user = new UserDataVo();
        user.setId("1");
        user.setName(BasicConstant.USER_NAME);
        UserDataVo userFromMongo = userDataDao.queryOne(user);
        return BasicJspUtil.getBasicView();
    }

    /**
     * 获取mongo数据
     */
    @RequestMapping(value = "/getVoList")
    public ModelAndView getVoList(ModelAndView mv) throws Exception {
        UserDataVo user = new UserDataVo();
        user.setName(BasicConstant.USER_NAME);
        List<UserDataVo> userFromMongo = userDataDao.queryList(user);
        return BasicJspUtil.getBasicView();
    }

    public void dealWithOldVo(UserDataVo userDataMongo, UserDataVo userDataNew) {

    }

}
