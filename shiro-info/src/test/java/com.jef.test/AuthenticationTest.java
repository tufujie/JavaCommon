package com.jef.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;

/**
 * 身份认证
 * 包含从文件读入和普通读入
 * @author Jef
 */
public class AuthenticationTest {

    @Test
    public void testAuthentication() {
        SecurityManager securityManager = getSecurityManagerTwo();
        // 把securityManager实例绑定到SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
        // 得到当前执行的用户
        Subject subject = SecurityUtils.getSubject();
        //认证实体，当前进来的用户
        // 创建token令牌，用户名/密码，模拟登录的用户输入的用户名和密码，正确的userName，passWord
        UsernamePasswordToken token = new UsernamePasswordToken("Jef","123456");
        // 身份认证
        String userName = token.getUsername();
        try {
            subject.login(token);
            System.out.println(userName + "登录成功");
        } catch (AuthenticationException e) {
            // login的接口函数  void login(AuthenticationToken var1) throws AuthenticationException;所以直接抓AuthenticationException异常即可
            // 身份认证失败即抛出此异常
            System.out.println(userName + "登录失败，" + e.getMessage());
        }
        // 错误的账号，也就等于密码无论输入什么也都是错误的
        token = new UsernamePasswordToken("Da","123456");
        userName = token.getUsername();
        try {
            subject.login(token);
            System.out.println(userName + "登录成功");
        } catch (AuthenticationException e) {
            System.out.println(userName + "登录失败，" + e.getMessage());
        }
        // 正确的账号，错误的密码
        token = new UsernamePasswordToken("Ran","123456");
        userName = token.getUsername();
        try {
            subject.login(token);
            System.out.println(userName + "登录成功");
        } catch (AuthenticationException e) {
            System.out.println(userName + "登录失败，" + e.getMessage());
        }
        // 登出
        subject.logout();
    }

    /**
     * 方法1获取SecurityManager对象
     */
    private SecurityManager getSecurityManagerOne() {
        // IniSecurityManagerFactory方法在1.4.0中被注解标志为不建议使用
        // 读取配置文件，初始化SecurityManager工厂，模拟数据库中数据
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-user.ini");
        // 获取securityManager实例
        SecurityManager securityManager = factory.getInstance();
        return securityManager;
    }

    /**
     * 方法2获取SecurityManager对象
     */
    private SecurityManager getSecurityManagerTwo() {
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();
        // 来自数据库的用户名/密码
        simpleAccountRealm.addAccount("Jef", "123456");
        securityManager.setRealm(simpleAccountRealm);
        return securityManager;
    }
}
