package com.jef.service.impl;

import com.jef.service.IAxeService;

/**
 * 斧头的服务实现，木斧
 */
public class WoodAxeServiceImpl implements IAxeService {

    @Override
    public String chop() {
        return "木斧砍柴最慢";
    }
}
