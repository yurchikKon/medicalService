package com.paymentApplication.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Entity
@Table(name = "transaction")
public class PaymentTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "amount")
    BigDecimal amount;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @OneToOne
    @JoinColumn(name = "account_receiver")
    Account receiver;

    @OneToOne
    @JoinColumn(name = "account_sender")
    Account sender;

    @OneToOne
    @JoinColumn(name = "payment_method_id")
    PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "currency_id")
    Currency currency;

    @ManyToOne
    @JoinColumn(name = "status_id")
    Status status;

    @OneToMany
    List<TransactionLog> transactionLog;
}
