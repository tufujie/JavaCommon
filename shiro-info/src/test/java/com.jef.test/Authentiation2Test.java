package com.jef.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;

public class Authentiation2Test {
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    // 角色列表
    private static final String ROLEONE = "admin";
    private static final String ROLETWO = "user";
    private static final String ROLETHREE = "employee";

    @Before
    public void addUser() {
        // 赋予Jef两个角色
        simpleAccountRealm.addAccount("Jef", "123456", ROLEONE, ROLETWO);
    }

    @Test
    public void testAuthentication() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(simpleAccountRealm);
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("Jef","123456");
        subject.login(token);
        String userName = token.getUsername();
        System.out.println(userName + (subject.isAuthenticated() ? "已认证" : "未认证"));
        // 一次认证一个角色数据
        String role = ROLEONE;
        try {
            subject.checkRole(role);
            System.out.println(userName + "具有角色" + role);
        } catch (AuthorizationException e) {
            System.out.println(userName + "不具有角色，" + role + e.getMessage());
        }
        role = ROLETWO;
        try {
            subject.checkRole(role);
            System.out.println(userName + "具有角色" + role);
        } catch (AuthorizationException e) {
            System.out.println(userName + "不具有角色，" + role + e.getMessage());
        }
        role = ROLETHREE;
        try {
            subject.checkRole(role);
            System.out.println(userName + "具有角色" + role);
        } catch (AuthorizationException e) {
            System.out.println(userName + "不具有角色，" + role + e.getMessage());
        }
        // 一次检查多个角色
        try {
            subject.checkRoles(ROLEONE, ROLETWO);
            System.out.println(userName + "具有上述角色");
        } catch (AuthorizationException e) {
            System.out.println(userName + "不具有上述角色，" + role + e.getMessage());
        }
        try {
            subject.checkRoles(ROLEONE, ROLETHREE);
            System.out.println(userName + "具有上述角色");
        } catch (AuthorizationException e) {
            System.out.println(userName + "不具有上述角色，" + role + e.getMessage());
        }
    }
}
