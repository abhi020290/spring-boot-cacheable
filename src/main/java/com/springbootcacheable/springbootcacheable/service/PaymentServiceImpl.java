package com.springbootcacheable.springbootcacheable.service;

import com.springbootcacheable.springbootcacheable.entity.PaymentEntity;
import com.springbootcacheable.springbootcacheable.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    @Caching(evict = {@CacheEvict(value = "Payments", allEntries = true),
            @CacheEvict(value = "ByPaymentId", key = "#entity.paymentId"),
            @CacheEvict(value = "ByTransactionId", key = "#entity.transactionId"),
            @CacheEvict(value = "ByAuthStatus", key = "#entity.authStatus")},
            put = {@CachePut(value = "ByPaymentId", key = "#entity.paymentId"),
                    @CachePut(value = "ByTransactionId", key = "#entity.transactionId"),
                    @CachePut(value = "ByAuthStatus", key = "#entity.authStatus")}
    )
    public PaymentEntity save(PaymentEntity entity) {
        log.info("PaymentServiceImpl save() invoked {} ", entity.getPaymentId());
        return paymentRepository.save(entity);
    }

    @Override
    @Caching(evict = {@CacheEvict(value = "Payments", allEntries = true),
            @CacheEvict(value = "ByPaymentId", key = "#entity.paymentId"),
            @CacheEvict(value = "ByTransactionId", key = "#entity.transactionId"),
            @CacheEvict(value = "ByAuthStatus", key = "#entity.authStatus")
    })
    public PaymentEntity update(PaymentEntity entity) {
        log.info("PaymentServiceImpl update() invoked {} ", entity.getPaymentId());
        return paymentRepository.save(entity);
    }

    @Override
    @Cacheable(value = "ByPaymentId", key = "#paymentId")
    public PaymentEntity findByPaymentId(String paymentId) {
        log.info("PaymentServiceImpl findByPaymentId() invoked {} ", paymentId);
        Optional<PaymentEntity> paymentEntity = paymentRepository.findByPaymentId(paymentId);
        return paymentEntity.orElse(null);
    }

    @Override
    @Cacheable(value = "ByTransactionId", key = "#transactionId")
    public List<PaymentEntity> findByTransactionId(String transactionId) {
        log.info("PaymentServiceImpl findByTransactionId() invoked {} ", transactionId);
        return paymentRepository.findByTransactionId(transactionId);
    }

    @Override
    @Cacheable(value = "ByAuthStatus", key = "#authStatus")
    public List<PaymentEntity> findByAuthStatus(String authStatus) {
        log.info("PaymentServiceImpl findByAuthStatus() invoked {} ", authStatus);
        return paymentRepository.findByAuthStatus(authStatus);
    }

    @Override
    @Cacheable(value = "Payments")
    public List<PaymentEntity> findAll() {
        log.info("PaymentServiceImpl findAll() invoked");
        return paymentRepository.findAll();
    }
}
