package com.jef.dubbo.demo;

import java.util.List;

/**
 * dubbo接口
 * @author Jef
 * @date 2021/4/26
 */
public interface DemoService {
    List<String> getPermissions(Long id);
}
