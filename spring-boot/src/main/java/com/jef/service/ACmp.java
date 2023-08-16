package com.jef.service;

import com.yomahub.liteflow.core.NodeComponent;
import org.springframework.stereotype.Component;

/**
 * @author tufujie
 * @date 2023/8/16
 */
@Component("serviceA")
public class ACmp extends NodeComponent {

    @Override
    public void process() {
        //do your business
        System.out.println("服务A业务执行完毕");
    }
}
