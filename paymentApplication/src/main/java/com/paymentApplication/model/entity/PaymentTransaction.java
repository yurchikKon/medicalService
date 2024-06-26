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
    @JoinColumn(name = "account_receiver", referencedColumnName = "id")
    Account receiver;

    @OneToOne
    @JoinColumn(name = "account_sender", referencedColumnName = "id")
    Account sender;

    @OneToOne
    @JoinColumn(name = "payment_method_id", referencedColumnName = "id")
    PaymentMethod paymentMethod;

    @OneToOne
    @JoinColumn(name = "currency_id", referencedColumnName = "id")
    Currency currency;

    @OneToOne
    @JoinColumn(name = "status_id", referencedColumnName = "id")
    Status status;

    @OneToMany
    List<TransactionLog> transactionLog;
}
