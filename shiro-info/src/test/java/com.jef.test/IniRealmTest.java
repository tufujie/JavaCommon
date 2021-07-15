package com.jef.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * IniRealm的demo
 *
 * @author Jef
 * @create 2018/5/12 8:29
 */
public class IniRealmTest {

    @Test
    public void testAuthentication() {
        IniRealm iniRealm = new IniRealm("classpath:shiro-user.ini");
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(iniRealm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("Jef", "123456");
        String userName = token.getUsername();
        // 正确的认证信息
        try {
            subject.login(token);
            System.out.println(userName + "登录成功");
        } catch (AuthenticationException e) {
            System.out.println(userName + "登录失败，" + e.getMessage());
        }
        // 正确的角色
        String role = "admin";
        try {
            subject.checkRole(role);
            System.out.println(userName + "拥有角色=" + role);
        } catch (AuthorizationException e) {
            System.out.println(userName + "未拥有角色=" + role);
        }
        role = "superAdmin";
        try {
            subject.checkRole(role);
            System.out.println(userName + "拥有角色=" + role);
        } catch (AuthorizationException e) {
            System.out.println(userName + "未拥有角色=" + role);
        }
        // 正确的授权
        String permission = "user:update";
        try {
            subject.checkPermission(permission);
            System.out.println(userName + "拥有权限=" + permission);
        } catch (AuthorizationException e) {
            System.out.println(userName + "未拥有权限=" + permission);
        }
        // 错误的授权
        permission = "user:delete";
        try {
            subject.checkPermission(permission);
            System.out.println(userName + "拥有权限=" + permission);
        } catch (AuthorizationException e) {
            System.out.println(userName + "未拥有权限=" + permission);
        }

        // 错误的认证信息
        token = new UsernamePasswordToken("Da", "123456");
        userName = token.getUsername();
        try {
            subject.login(token);
            System.out.println(userName + "登录成功");
        } catch (AuthenticationException e) {
            System.out.println(userName + "登录失败，" + e.getMessage());
        }
    }

}
