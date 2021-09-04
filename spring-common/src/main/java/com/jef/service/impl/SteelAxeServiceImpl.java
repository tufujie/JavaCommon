package com.jef.service.impl;

import com.jef.service.IAxeService;

/**
 * 斧头的服务实现，钢斧
 */
public class SteelAxeServiceImpl implements IAxeService {

    @Override
    public String chop() {
        return "钢斧砍柴好快";
    }
}
