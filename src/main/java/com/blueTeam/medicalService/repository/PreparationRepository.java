package com.blueTeam.medicalService.repository;

import com.blueTeam.medicalService.entity.Preparation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreparationRepository extends JpaRepository<Preparation, Long> {
}
