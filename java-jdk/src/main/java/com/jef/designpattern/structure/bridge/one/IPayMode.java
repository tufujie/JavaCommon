package com.jef.designpattern.structure.bridge.one;

public interface IPayMode {

    /**
     * 风险校验
     *
     * @param uId 用户ID
     * @return boolean
     * @author Jef
     * @date 2021/12/7
     */
    boolean security(String uId);
}
