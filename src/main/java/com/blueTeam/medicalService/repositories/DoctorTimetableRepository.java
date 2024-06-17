package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.DoctorTimetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;

@Repository
public interface DoctorTimetableRepository extends JpaRepository<DoctorTimetable, Long> {

    DoctorTimetable findByDoctorIdAndDayOfWeek(Long id, DayOfWeek dayOfWeek);
}
