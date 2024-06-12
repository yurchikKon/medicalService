package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.DoctorAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface DoctorAppointmentRepository extends JpaRepository<DoctorAppointment, Long> {
    List<DoctorAppointment> findAllDoctorsByDateTime(LocalDateTime dateTime);
}
