package com.jef.entity;

import java.io.Serializable;

/**
 * 数据库配置表
 * @author Jef
 * @create 2018/7/13 14:09
 */
public class Config implements Serializable {

    private static final long serialVersionUID = -6279425303256378760L;
    private String id;
    private Integer level;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
