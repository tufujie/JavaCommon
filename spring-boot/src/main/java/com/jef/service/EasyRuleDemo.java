package com.jef.service;

import com.jef.entity.EasyRule;
import com.jef.entity.EasyRuleFact;
import com.jef.util.ClassInstance;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tufujie
 * @date 2023/8/18
 */
public class EasyRuleDemo {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, ClassNotFoundException, InstantiationException {
        // 定义规则列表，一次性全部定义，可以从数据库直接配置，配置类和规则，这里模拟从数据库取出已经配置的多个
        List<EasyRule> easyRuleList = new ArrayList<>();
        EasyRule easyRuleRain = new EasyRule();
        easyRuleRain.setRuleName("getWeatherRule");
        easyRuleRain.setClassName("com.jef.util.EasyRuleUtil");

        EasyRule easyRuleSun = new EasyRule();
        easyRuleSun.setRuleName("getSunRule");
        easyRuleSun.setClassName("com.jef.util.EasyRuleUtil");

        easyRuleList.add(easyRuleRain);
        easyRuleList.add(easyRuleSun);

        Rules rules = new Rules();
        for (EasyRule easyRule : easyRuleList) {
            Class clazz = ClassInstance.getInstance(easyRule.getClassName());
            Object obj = clazz.newInstance();
            Method method = clazz.getDeclaredMethod(easyRule.getRuleName());
            rules.register(method.invoke(obj));
        }

        RulesEngine rulesEngine = new DefaultRulesEngine();
        // 在已知的事实上执行规则
        // 定义事实列表
        Facts facts = new Facts();
        facts.put("rain", true);
        rulesEngine.fire(rules, facts);
        System.out.println("------");

        facts = new Facts();
        facts.put("sun", true);
        rulesEngine.fire(rules, facts);
        System.out.println("------");

        // 组合事实，要应用的地方，配置不同的key和value在数据库，然后可以在数据库直接拉取规则
        System.out.println("开始组合");
        String businessNameKey = "business1";
        List<EasyRuleFact> easyRuleFactList = new ArrayList<>();
        EasyRuleFact easyRuleFactRain = new EasyRuleFact();
        easyRuleFactRain.setBusinessNameKey(businessNameKey);
        easyRuleFactRain.setFact("rain");

        EasyRuleFact easyRuleFactSun = new EasyRuleFact();
        easyRuleFactSun.setBusinessNameKey(businessNameKey);
        easyRuleFactSun.setFact("sun");

        easyRuleFactList.add(easyRuleFactRain);
        easyRuleFactList.add(easyRuleFactSun);

        facts = new Facts();
        for (EasyRuleFact easyRuleFact : easyRuleFactList) {
            facts.put(easyRuleFact.getFact(), true);
        }
        rulesEngine.fire(rules, facts);
    }

}