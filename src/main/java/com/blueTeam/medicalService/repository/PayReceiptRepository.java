package com.blueTeam.medicalService.repository;

import com.blueTeam.medicalService.entity.PayReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayReceiptRepository extends JpaRepository<PayReceipt, Long> {
}
