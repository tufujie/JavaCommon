package com.jef.dubbo.entity;

import java.io.Serializable;

/**
 * @author Jef
 * @date 2021/7/8
 */
public class User implements Serializable {
    private static final long serialVersionUID = 4916341296609670656L;
    private String id;

    private String name;

    private String phone;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}