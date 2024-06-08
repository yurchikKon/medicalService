package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Long, Patient> {
}
