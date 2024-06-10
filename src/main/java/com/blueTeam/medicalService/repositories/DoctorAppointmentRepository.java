package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.DoctorAppointment;
import com.blueTeam.medicalService.entities.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorAppointmentRepository extends JpaRepository<DoctorAppointment, Long> {

    @Query("SELECT d FROM DoctorAppointment d WHERE d.id = :id and d.status = 'scheduled' and FUNCTION('DATE_TRUNC', 'day', d.dateTime)" +
        " = FUNCTION('DATE_TRUNC', 'day', :date)")
    List<DoctorAppointment> findAllScheduledByDoctorIdAndDate(@Param("id") Long id, @Param("date") LocalDate localDate);

    Optional<DoctorAppointment> findByDoctorIdAndPatientIdAndDateTime(Long doctorId, Long patientId, LocalDateTime dateTime);

    List<DoctorAppointment> findAllByPatientIdAndStatus(Long patientId, Status status);
}

