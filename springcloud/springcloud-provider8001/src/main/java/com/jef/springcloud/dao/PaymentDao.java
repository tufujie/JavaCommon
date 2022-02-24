package com.jef.springcloud.dao;

import com.jef.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    // 插入
    public int insert(Payment payment);
    // 获取
    public Payment getPaymentById(@Param("id") Long id);
}