package com.blueTeam.medicalService.repository;

import com.blueTeam.medicalService.entity.DoctorRemark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRemarkRepository extends JpaRepository<DoctorRemark, Long> {
}
