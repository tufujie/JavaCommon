package com.jef.codeclean;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.jef.context.REContext;
import com.jef.context.REContextManager;
import com.jef.entity.BaseJSONVo;
import com.jef.entity.ProjectVo;
import com.jef.entity.User;
import com.jef.service.IUserService;
import com.jef.util.REJSONUtils;
import com.jef.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;
import java.util.Map;

/**
 * 避免出现重复的代码（Don't Repeat Yourself），即 DRY 原则。
 * @author Jef
 * @date 2019/9/7
 */
@Controller
@RequestMapping(value = "donotRepeatYourself")
public class DonotRepeatYourselfController {

    @Autowired
    private IUserService userService;

    /**
     * 获取列表数据
     * @author Jef
     * @date 2019/9/7
     * @param searchParams 高级查询参数
     * @return com.jef.entity.BaseJSONVo
     */
    @RequestMapping(value = "getUserList")
    public BaseJSONVo getUserList(@RequestParam(value = "searchParams") String searchParams) {
        REContext context = REContextManager.getREContext();
        ProjectVo projectVo = (ProjectVo) context.get("loginProject");
        Map<String, Object> requestParamMap = getRequestParamsMap(searchParams, projectVo);
        List<User> userList = userService.getUserList(requestParamMap);
        return REJSONUtils.success(userList, 0, "操作成功");
    }

    /**
     * 导出列表数据
     * @author Jef
     * @date 2019/9/7
     * @param searchParams 高级查询参数
     * @return com.jef.entity.BaseJSONVo
     */
    @RequestMapping(value = "exportUserList")
    public BaseJSONVo exportUserList(@RequestParam(value = "searchParams") String searchParams) {
        REContext context = REContextManager.getREContext();
        ProjectVo projectVo = (ProjectVo) context.get("loginProject");
        Map<String, Object> requestParamMap = getRequestParamsMap(searchParams, projectVo);
        List<User> userList = userService.getUserList(requestParamMap);
        // 开始导出数据
        // 导出数据完成
        return REJSONUtils.success(userList, 0, "操作成功");
    }

   /**
    * 解析获取列表数据的高级查询参数
    * @author Jef
    * @date 2019/9/7
    * @param searchParams 高级查询参数
    * @param projectVo 当前项目vo
    * @return java.util.Map<java.lang.String,java.lang.Object>
    */
    private Map<String, Object> getRequestParamsMap(String searchParams, ProjectVo projectVo) {
        Map<String, Object> requestParamMap = Maps.newHashMap();
        if (StringUtils.isNotEmpty(searchParams)) {
            JSONObject searchParamObj = JSONObject.parseObject(searchParams);
            String projectIDs = searchParamObj.getString("projectID");
            List<String> projectIDList = StringUtils.getListFromString(projectVo.getId());
            if (StringUtils.isNotEmpty(projectIDs)) {
                projectIDList = StringUtils.getListFromString(projectIDs);
            }
            requestParamMap.put("projectIDList", projectIDList);
            String contractType = searchParamObj.getString("contractType");
            Integer status = searchParamObj.getInteger("status");
            requestParamMap.put("contractType", contractType);
            requestParamMap.put("status", status);
        }
        return requestParamMap;
    }
}