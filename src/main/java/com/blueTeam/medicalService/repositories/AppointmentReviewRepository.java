package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.AppointmentReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentReviewRepository extends JpaRepository<Long, AppointmentReview> {
}
