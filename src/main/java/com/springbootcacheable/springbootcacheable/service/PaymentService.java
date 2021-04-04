package com.springbootcacheable.springbootcacheable.service;

import com.springbootcacheable.springbootcacheable.entity.PaymentEntity;
import org.springframework.stereotype.Service;

import java.util.List;

public interface PaymentService {
    PaymentEntity save(PaymentEntity entity);

    List<PaymentEntity> findAll();

    PaymentEntity findByPaymentId(String paymentId);

    List<PaymentEntity> findByTransactionId(String transactionId);

    List<PaymentEntity> findByAuthStatus(String authStatus);

    PaymentEntity update(PaymentEntity entity);

}
