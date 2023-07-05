package com.jef.designpattern.structure.flyweight.one;

/**
 * 享元状态
 * @author Jef
 * @date 2019/2/21
 */
public class AuthorizationFlyweight implements FlyWeight {
    /**
     * 内部状态，安全实体
     */
    private String securityEntity;
    /**
     * 内部状态，权限
     */
    private String permit;

    /**
     * 构造方法，传入状态数据
     * @param state 状态数据，包含安全实体和权限的数据，用","分隔
     */
    public AuthorizationFlyweight(String state) {
        String ss[] = state.split(",");
        securityEntity = ss[0];
        permit = ss[1];
    }

    public String getSecurityEntity() {
        return securityEntity;
    }

    public void setSecurityEntity(String securityEntity) {
        this.securityEntity = securityEntity;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    @Override
    public boolean match(String secutiryEntity, String permit) {
        if (this.securityEntity.equals(secutiryEntity)
                && this.permit.equals(permit)) {
            return true;
        }
        return false;
    }
}