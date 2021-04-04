package com.springbootcacheable.springbootcacheable.repository;

import com.datastax.oss.driver.api.core.DefaultConsistencyLevel;
import com.springbootcacheable.springbootcacheable.entity.PaymentEntity;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Consistency;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository extends CassandraRepository<PaymentEntity,String> {

    @Consistency(value = DefaultConsistencyLevel.ONE)
    Optional<PaymentEntity> findByPaymentId(String paymentId);

    @AllowFiltering
    @Consistency(value = DefaultConsistencyLevel.ONE)
    List<PaymentEntity> findByTransactionId(String transactionId);

    @AllowFiltering
    @Consistency(value = DefaultConsistencyLevel.ONE)
    List<PaymentEntity> findByAuthStatus(String authStatus);

}
