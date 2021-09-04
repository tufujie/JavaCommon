package com.jef.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息
 * 以用户为核心
 * @author Jef
 * @create 2018/5/15 19:18
 */
public class User extends BaseDomain {
    private static final long serialVersionUID = -8514215816882785376L;
    // Redis缓存用户key
    public static final String OBJECT_KEY = "User";

    private Long id;

    private String name;

    private String password;

    private String phone;

    private int age;

    private Integer permission;

    private Integer admin;

    private OrderInfo orderInfo;

    public User() {

    }

    public User(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public User(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public Integer getAdmin() {
        return admin;
    }

    public void setAdmin(Integer admin) {
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "id=" + this.getId() + "；名称=" + this.getName() + "；年龄=" + this.getAge() + "；电话=" + this.getPhone();
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }
}
