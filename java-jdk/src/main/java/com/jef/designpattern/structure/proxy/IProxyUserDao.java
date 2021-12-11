package com.jef.designpattern.structure.proxy;

public interface IProxyUserDao {

    @ProxySelect("select userName from user where id = #{uId}")
    String queryUserInfo(String uId);
}