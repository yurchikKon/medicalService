package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.PatientsDiagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientDiagnosisRepository extends JpaRepository<Long, PatientsDiagnosis> {
}