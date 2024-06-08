package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.Preparation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreparationRepository extends JpaRepository<Long, Preparation> {
}
