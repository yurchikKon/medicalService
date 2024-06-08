package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.SpecialDoctorDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialDoctorDirectionRepository extends JpaRepository<Long, SpecialDoctorDirection> {
}
