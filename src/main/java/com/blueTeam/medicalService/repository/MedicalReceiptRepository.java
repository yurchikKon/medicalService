package com.blueTeam.medicalService.repository;

import com.blueTeam.medicalService.entity.MedicalReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalReceiptRepository extends JpaRepository<MedicalReceipt, Long> {
}
