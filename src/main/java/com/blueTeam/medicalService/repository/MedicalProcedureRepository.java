package com.blueTeam.medicalService.repository;

import com.blueTeam.medicalService.entity.MedicalProcedure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalProcedureRepository extends JpaRepository<MedicalProcedure, Long> {
}
