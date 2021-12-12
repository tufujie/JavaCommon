package com.jef.designpattern.action.vistor.user;

import com.jef.designpattern.action.vistor.visitor.Visitor;

// 基础用户信息
public abstract class User {

    public String name;      // 姓名
    public String identity;  // 身份；重点班、普通班 | 特级教师、普通教师、实习教师
    public String clazz;     // 班级

    public User(String name, String identity, String clazz) {
        this.name = name;
        this.identity = identity;
        this.clazz = clazz;
    }

    /**
     * 核心访问方法
     *
     * @param visitor 访问者
     * @author Jef
     * @date 2021/12/12
     */
    public abstract void accept(Visitor visitor);

}
