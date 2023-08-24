package com.jef.rule;

import com.jef.util.ReflectionUtil;

import java.util.Map;

/**
 * @author tufujie
 * @date 2023/8/24
 */
public abstract class AbstractRule {

    public boolean pickAndHandle(Map<String, Object> map, Object obj, String ruleCode) {
        int pick = 0;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Object value = ReflectionUtil.invokeGetter(obj, entry.getKey());
            if (entry.getValue().equals(value)) {
                pick++;
            } else {
                System.out.println("key=" + entry.getKey() + ",真实value=" + value + ",设置的value=" + entry.getValue());
            }
        }
        if (pick != 0 && pick == map.size()) {
            System.out.println("所有参数都匹配，做些业务");
            // 调用规则开始
            System.out.println("规则编码=" + ruleCode + "，调用规则触发完毕");
            // 调用规则结束
            handle(obj);
            return true;
        } else {
            System.out.println("不是所有参数都匹配，做些业务");
            return false;
        }
    }

    public abstract void handle(Object obj);


}