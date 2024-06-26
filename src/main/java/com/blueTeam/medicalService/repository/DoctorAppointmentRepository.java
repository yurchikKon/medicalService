package com.blueTeam.medicalService.repository;

import com.blueTeam.medicalService.entity.DoctorAppointment;
import com.blueTeam.medicalService.entity.enums.Notification;
import com.blueTeam.medicalService.entity.enums.Status;
import org.springframework.data.jpa.repository.EntityGraph;
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

    @Query("SELECT d FROM DoctorAppointment d WHERE d.doctor.id = :id and d.status = 'SCHEDULED' and CAST(d.dateTime as date ) =:date")
    List<DoctorAppointment> findAllScheduledByDoctorIdAndDate(@Param("id") Long id, @Param("date") LocalDate localDate);

    Optional<DoctorAppointment> findByDoctorIdAndPatientIdAndDateTime(Long doctorId, Long patientId, LocalDateTime dateTime);

    List<DoctorAppointment> findAllByPatientIdAndStatus(Long patientId, Status status);

    @EntityGraph(attributePaths = {"patient", "doctor"})
    @Query("SELECT d FROM DoctorAppointment d WHERE CAST(d.dateTime AS date) = CURRENT_DATE" + " AND d.status = :status AND d.notification = :notification")
    List<DoctorAppointment> findPlannedAppointments(@Param("status") Status status, @Param("notification") Notification notification);

    @Query("SELECT d FROM DoctorAppointment d WHERE cast(d.dateTime as date)= :date")
    List<DoctorAppointment> findAllByDate(@Param("date") LocalDate localDate);

}

