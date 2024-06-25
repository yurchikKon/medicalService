package com.blueTeam.medicalService.repository;

import com.blueTeam.medicalService.entity.SpecialDoctorDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialDoctorDirectionRepository extends JpaRepository<SpecialDoctorDirection, Long> {
}
