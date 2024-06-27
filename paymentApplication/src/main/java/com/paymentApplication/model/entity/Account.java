package com.paymentApplication.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    BigDecimal balance;

    @Column(name = "created_at")
    LocalDateTime createdAt;

    @OneToOne(cascade = ALL)
    @JoinColumn(name = "user_id")
    User user;

    @ManyToOne(cascade = ALL)
    @JoinColumn(name = "currency_id")
    Currency currency;

    @OneToOne(mappedBy = "sender")
    PaymentTransaction senderTransaction;

    @OneToOne(mappedBy = "receiver")
    PaymentTransaction receiverTransaction;
}
