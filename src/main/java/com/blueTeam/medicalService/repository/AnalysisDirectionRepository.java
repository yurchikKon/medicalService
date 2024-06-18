package com.blueTeam.medicalService.repository;

import com.blueTeam.medicalService.entity.AnalysisDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisDirectionRepository extends JpaRepository<AnalysisDirection, Long> {

}
