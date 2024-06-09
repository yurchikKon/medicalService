package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.Diagnosis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiagnosisRepository extends JpaRepository<Diagnosis, Long> {
}
