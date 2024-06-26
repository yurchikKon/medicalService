package com.paymentApplication.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = PRIVATE)
@Entity
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    List<PaymentTransaction> transaction;
}
