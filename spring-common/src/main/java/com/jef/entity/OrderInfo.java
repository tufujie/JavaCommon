package com.jef.entity;

import java.io.Serializable;

public class OrderInfo extends BaseDomain {
    private static final long serialVersionUID = -8130617419334171401L;
    private Long id;

    private String extraOrderId;

    private Long shopId;

    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExtraOrderId() {
        return extraOrderId;
    }

    public void setExtraOrderId(String extraOrderId) {
        this.extraOrderId = extraOrderId == null ? null : extraOrderId.trim();
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}