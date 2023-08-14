package com.jef.demo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tufujie
 * @date 2023/8/14
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -8514215816882785376L;
    // Redis缓存用户key
    public static final String OBJECT_KEY = "User";

    private Long id;

    private String name;

    private Integer admin;
}