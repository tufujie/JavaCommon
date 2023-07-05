package com.jef.designpattern.structure.flyweight.one;

/**
 * 描述授权数据的数据model
 * @author Jef
 * @date 2019/2/21
 */
public class AuthorizationModel {
    /**
     * 人员，模拟实际情况中可能是用户ID
     */
    private String user;
    /**
     * 安全实体
     */
    private String securityEntity;
    /**
     * 权限
     */
    private String permit;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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
}