package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.DoctorAppointment;
import com.blueTeam.medicalService.entities.enums.Notification;
import com.blueTeam.medicalService.entities.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorAppointmentRepository extends JpaRepository<DoctorAppointment, Long> {

    @Query("SELECT a FROM DoctorAppointment a WHERE CAST(a.dateTime AS date) = CURRENT_DATE"
            + " AND a.status = :status"
            + " AND a.notification = :notification"
    )
    List<DoctorAppointment> findPlannedAppointments(@Param("status") Status status,
                                                    @Param("notification") Notification notification);
}
