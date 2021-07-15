package com.jef.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * JdbcRealm的demo
 *
 * @author Jef
 * @create 2018/5/12 8:49
 */
public class JdbcRealmTest {
    DruidDataSource dataSource = new DruidDataSource();
    {
        dataSource.setUrl("jdbc:mysql://localhost:3306/test");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
    }

    @Test
    public void testAuthentication() {
        JdbcRealm jdbcRealm = new JdbcRealm();
        // 设置数据源
        jdbcRealm.setDataSource(dataSource);
        // 设置权限为开，默认为false的情况下是查不到权限，会报错
        jdbcRealm.setPermissionsLookupEnabled(true);
        /* 下面6行是分别使用SQL的形式进行查看，其中认证用户使用的表名和字段名都有所变化，用户角色和角色权限使用的是默认的表，所以SQL是一样的
        如果表和字段和默认的一样，则可以注释下面的6行代码 */
        String authenticationQuery = "select pass_word from tb_users where user_name = ?";
        jdbcRealm.setAuthenticationQuery(authenticationQuery);
        String userRolesQuery = "select role_name from user_roles where username = ?";
        jdbcRealm.setUserRolesQuery(userRolesQuery);
        String permissionsQuery = "select permission from roles_permissions where role_name = ?";
        jdbcRealm.setPermissionsQuery(permissionsQuery);
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        securityManager.setRealm(jdbcRealm);
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
