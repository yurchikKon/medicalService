package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.AnalysisDirection;
import com.blueTeam.medicalService.entities.enums.Usage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalysisDirectionRepository extends JpaRepository<AnalysisDirection, Long> {

}
