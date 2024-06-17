package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.AppointmentReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentReviewRepository extends JpaRepository<AppointmentReview, Long> {

    Optional<AppointmentReview> findByDoctorsAppointmentId(Long appointmentId);
}
