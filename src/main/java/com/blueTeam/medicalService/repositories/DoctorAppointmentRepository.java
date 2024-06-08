package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.DoctorsAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorAppointmentRepository extends JpaRepository<Long, DoctorsAppointment> {
}
