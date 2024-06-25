package com.blueTeam.medicalService.repository;

import com.blueTeam.medicalService.entity.DoctorTimetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;

@Repository
public interface DoctorTimetableRepository extends JpaRepository<DoctorTimetable, Long> {

    DoctorTimetable findByDoctorIdAndDayOfWeek(Long id, DayOfWeek dayOfWeek);
}
