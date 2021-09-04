package com.jef.service;

import java.util.List;

/**
 * dubbo接口
 * @author Jef
 * @date 2021/4/26
 */
public interface IDubboDemoService {
    List<String> getPermissions(Long id);
}
