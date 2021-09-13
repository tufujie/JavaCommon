package com.jef.dubbo.consumer;

import com.jef.dubbo.api.DemoService;

/**
 * 功能
 *
 * @author Jef
 * @date 2021/9/13
 */
public class FunctionAction {
    private DemoService demoService;

    public DemoService getDemoService() {
        return demoService;
    }

    public void setDemoService(DemoService demoService) {
        this.demoService = demoService;
    }
}