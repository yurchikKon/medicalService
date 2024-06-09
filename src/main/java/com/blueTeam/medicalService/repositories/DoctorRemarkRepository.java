package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.DoctorRemark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRemarkRepository extends JpaRepository<DoctorRemark, Long> {
}
