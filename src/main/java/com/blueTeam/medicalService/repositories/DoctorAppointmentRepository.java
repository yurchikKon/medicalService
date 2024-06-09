package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.DoctorAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DoctorAppointmentRepository extends JpaRepository<DoctorAppointment, Long> {

    @Query("SELECT d FROM DoctorAppointment d WHERE d.id = :id and FUNCTION('DATE_TRUNC', 'day', d.dateTime)" +
        " = FUNCTION('DATE_TRUNC', 'day', :date)")
    List<DoctorAppointment> findAllByDoctorIdAndDate(@Param("id") Long id, @Param("date") LocalDate localDate);
}

