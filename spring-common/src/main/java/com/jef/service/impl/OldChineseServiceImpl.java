package com.jef.service.impl;

import com.jef.service.IAxeService;
import com.jef.service.IPersonServive;

/**
 * 人的服务实现，古代中国人
 */
public class OldChineseServiceImpl implements IPersonServive {
    private IAxeService axeService;

    public IAxeService getAxeService() {
        return axeService;
    }

    public void setAxeService(IAxeService axeService) {
        this.axeService = axeService;
    }

    @Override
    public void useAxe() {
        System.out.println("古代中国人使用石斧");
        System.out.println(axeService.chop());
    }
}
