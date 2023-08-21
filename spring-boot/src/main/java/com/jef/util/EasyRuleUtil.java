package com.jef.util;

import org.jeasy.rules.api.Rule;
import org.jeasy.rules.core.RuleBuilder;
import org.jeasy.rules.mvel.MVELRule;

import static com.jef.service.DecreaseTemperatureAction.decreaseTemperature;
import static com.jef.service.HighTemperatureCondition.itIsHot;

/**
 * @author tufujie
 * @date 2023/8/18
 */
public class EasyRuleUtil {
    /**
     * 定义一系列的场景
     * 下雨
     *
     * @return 规则
     */
    public static Rule getWeatherRule() {
        return new RuleBuilder()
                .name("weather rain rule")
                .description("如果下雨就带伞")
                .when(facts -> Boolean.TRUE.equals(facts.get("rain")))
                .then(facts -> {
                    System.out.println("下雨了，要带伞!");
                })
                .build();
    }

    /**
     * 定义一系列的场景
     * 下雨
     *
     * @return 规则
     */
    public static Rule getSunRule() {
        return new RuleBuilder()
                .name("weather sun rule")
                .description("如果大太阳就戴遮阳帽")
                .when(facts -> Boolean.TRUE.equals(facts.get("rain")))
                .then(facts -> {
                    System.out.println("大太阳了，要戴遮阳帽!");
                })
                .build();
    }

    public static Rule getAgeRule() {
        Rule ageRule = new MVELRule()
                .name("age rule")
                .description("Check if person's age is > 18 and marks the person as adult")
                .priority(1)
                .when("person.age > 18")
                .then("person.setAdult(true);");
        return ageRule;
    }

    public static Rule getAlcoholRule() {
        Rule ageRule = new MVELRule()
                .name("alcohol rule")
                .description("children are not allowed to buy alcohol")
                .priority(2)
                .when("person.isAdult() == false")
                .then("System.out.println(\"Shop: Sorry \" + person.name +  \", you are not allowed to buy alcohol\");");
        return ageRule;
    }

    public static Rule getAlcoholAuditRule() {
        Rule ageRule = new MVELRule()
                .name("alcohol rule")
                .description("children are not allowed to buy alcohol")
                .priority(3)
                .when("person.isAdult() == true")
                .then("System.out.println(\"Shop: Ok \" + person.name +  \", you are allowed to buy alcohol\")");
        return ageRule;
    }

    public static Rule getTemperatureRule() {
        Rule airConditioningRule = new RuleBuilder()
                .name("air conditioning rule")
                .when(itIsHot())
                .then(decreaseTemperature())
                .build();
        return airConditioningRule;
    }

}