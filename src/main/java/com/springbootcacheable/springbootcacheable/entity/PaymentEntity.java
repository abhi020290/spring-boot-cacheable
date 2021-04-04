package com.springbootcacheable.springbootcacheable.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table("payment_details")
public class PaymentEntity {

    @PrimaryKeyColumn(value = "payment_id",ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private String paymentId;
    @Column("transaction_id")
    private String transactionId;
    @Column("auth_status")
    private String authStatus;
    @Column("amount")
    private double amount;
    @Column("create_date")
    private Date createDate = new Date();
}
