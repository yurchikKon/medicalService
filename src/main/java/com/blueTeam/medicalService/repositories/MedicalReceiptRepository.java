package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.MedicalReceipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalReceiptRepository extends JpaRepository<MedicalReceipt, Long> {
}
