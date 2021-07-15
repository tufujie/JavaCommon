package com.jef.util;

import com.jef.constant.BasicConstant;
import com.jef.entity.User;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jef
 * @date 2018/8/7 19:33
 */
public class BeanMapUtilTest {

    @Test
    public void testBuildMap() {
        // javaBean设值
        User user = new User();
        user.setId(1L);
        user.setName(BasicConstant.USER_NAME);
        user.setPhone(BasicConstant.USER_PHONE);
        // 调用mapBuild自动装配
        Map<String, Object> map = new HashMap<>();
        BeanMapUtil.mapBuild(user, map);
        // 等效于下面
/*        Map<Object, Object> newMap = new HashMap<>();
        newMap.put("id", 1L);
        newMap.put("name", BasicConstant.USER_NAME);
        newMap.put("phone", BasicConstant.USER_PHONE);*/

        // 输出效果
        for (Map.Entry entry : map.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
        }
    }

}
