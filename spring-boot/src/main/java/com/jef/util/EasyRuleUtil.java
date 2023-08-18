package com.jef.util;

import org.jeasy.rules.api.Rule;
import org.jeasy.rules.core.RuleBuilder;

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
}