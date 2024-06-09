package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.AnalysisDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisDirectionRepository extends JpaRepository<AnalysisDirection, Long> {
}
