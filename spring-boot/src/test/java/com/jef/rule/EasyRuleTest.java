package com.jef.rule;

import com.jef.entity.Person;
import com.jef.util.EasyRuleUtil;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.InferenceRulesEngine;
import org.junit.jupiter.api.Test;

/**
 * @author tufujie
 * @date 2023/8/21
 */
public class EasyRuleTest {

    /**
     * 店铺买酒测试
     */
    @Test
    public void testShop() {
        // 创建一个实际事实的示例
        Person tom = new Person("Tom", 14);
        Facts facts = new Facts();
        facts.put("person", tom);

        // 创建规则
        Rule ageRule = EasyRuleUtil.getAgeRule();
        Rule alcoholRule = EasyRuleUtil.getAlcoholRule();
        Rule acceptedAuditRule = EasyRuleUtil.getAlcoholAuditRule();

        // 注册规则集合
        Rules rules = new Rules();
        rules.register(ageRule);
        rules.register(alcoholRule);
        rules.register(acceptedAuditRule);

        // 创建默认的规则引擎并执行规则
        RulesEngine rulesEngine = new DefaultRulesEngine();
        System.out.println("Tom: Hi! can I have some Vodka please?");
        rulesEngine.fire(rules, facts);

        // 创建一个实际事实的示例
        Person jef = new Person("jef", 20);
        facts.put("person", jef);

        System.out.println("Jef: Hi! can I have some Vodka please?");
        rulesEngine.fire(rules, facts);
    }

    @Test
    public void testAirCondition() {
        // 定义事实
        Facts facts = new Facts();
        facts.put("temperature", 30);

        // 定义规则
        Rule airConditioningRule = EasyRuleUtil.getTemperatureRule();
        Rules rules = new Rules();
        rules.register(airConditioningRule);

        // 已知事实基础上执行规则
        RulesEngine rulesEngine = new InferenceRulesEngine();
        rulesEngine.fire(rules, facts);
    }
}