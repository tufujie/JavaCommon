package com.jef.service.impl;

import com.jef.service.IAxeService;
import com.jef.service.IPersonServive;

/**
 * 人的服务实现，原始中国人
 */
public class OriginalChineseServiceImpl implements IPersonServive {
    private IAxeService axeService;

    public OriginalChineseServiceImpl(IAxeService axeService) {
        this.axeService = axeService;
    }

    @Override
    public void useAxe() {
        System.out.println("原始中国人使用木斧");
        System.out.println(axeService.chop());
    }
}
