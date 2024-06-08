package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.MedicalService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalServiceRepository extends JpaRepository<Long, MedicalService> {
}
