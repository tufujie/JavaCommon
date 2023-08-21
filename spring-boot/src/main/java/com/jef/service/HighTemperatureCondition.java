package com.jef.service;

import org.jeasy.rules.api.Condition;
import org.jeasy.rules.api.Facts;

/**
 * @author tufujie
 * @date 2023/8/21
 */
public class HighTemperatureCondition implements Condition {

    @Override
    public boolean evaluate(Facts facts) {
        Integer temperature = facts.get("temperature");
        return temperature > 25;
    }

    public static HighTemperatureCondition itIsHot() {
        return new HighTemperatureCondition();
    }

}