package com.jef.entity;

import java.io.Serializable;

/**
 * 用户统计，用于mongo测试
 * @author Jef
 * @create 20181207
 */
public class UserDataVo implements Serializable {

    private static final long serialVersionUID = -8995094277244990462L;
    private String id;

    private String name;

    private String password;

    private String phone;

    private Integer age;

    private String createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
