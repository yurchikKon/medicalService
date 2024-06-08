package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.Analysis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnalysisRepository extends JpaRepository<Long, Analysis> {
}
