package com.jef.springcloud.service;

import com.jef.springcloud.entity.Payment;

public interface PaymentService {
 // 插入
   public int insert(Payment payment);
 // 获取
  public Payment getPaymentById(Long id);
}