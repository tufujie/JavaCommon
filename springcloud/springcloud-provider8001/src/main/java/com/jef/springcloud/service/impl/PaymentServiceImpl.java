package com.jef.springcloud.service.impl;

import com.jef.springcloud.dao.PaymentDao;
import com.jef.springcloud.entities.Payment;
import com.jef.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;

    public int insert(Payment payment) {
        return paymentDao.insert(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}