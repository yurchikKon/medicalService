package com.blueTeam.medicalService.repository;

import com.blueTeam.medicalService.entity.PatientDiagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDiagnosisRepository extends JpaRepository<PatientDiagnosis, Long> {
}
