package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.PayReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PayReceiptRepository extends JpaRepository<PayReceipt, Long> {
}
