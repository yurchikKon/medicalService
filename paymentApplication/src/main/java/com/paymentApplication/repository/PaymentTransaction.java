package com.paymentApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTransaction extends JpaRepository<PaymentTransaction, Long> {
}
