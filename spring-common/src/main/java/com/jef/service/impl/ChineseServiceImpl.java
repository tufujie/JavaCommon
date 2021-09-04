package com.jef.service.impl;

import com.jef.service.IAxeService;
import com.jef.service.IPersonServive;

/**
 * 人的服务实现，现代中国人
 */
public class ChineseServiceImpl implements IPersonServive {
   private IAxeService axeService;

    public IAxeService getAxeService() {
        return axeService;
    }

    public void setAxeService(IAxeService axeService) {
        this.axeService = axeService;
    }

    @Override
    public void useAxe() {
        System.out.println("现代中国人使用钢斧");
        System.out.println(axeService.chop());
    }
}
