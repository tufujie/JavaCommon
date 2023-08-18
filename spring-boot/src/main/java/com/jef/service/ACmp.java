package com.jef.service;

import com.jef.entity.User;
import com.jef.util.ThreadLocalUtil;
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
        // 上下文信息
        User user = ThreadLocalUtil.getThreadLocalUser();
        System.out.println("用户信息=" + user + "；获取时的线程名称=" + Thread.currentThread().getName());
        System.out.println("服务A业务执行完毕");
        ThreadLocalUtil.removeThreadLocalName();
    }
}
