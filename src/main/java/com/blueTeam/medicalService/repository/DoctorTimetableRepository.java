package com.blueTeam.medicalService.repository;

import com.blueTeam.medicalService.entity.Doctor;
import com.blueTeam.medicalService.entity.DoctorTimetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DoctorTimetableRepository extends JpaRepository<DoctorTimetable, Long> {

    List<DoctorTimetable> findDoctorsTimetableByDayOfWeek(DayOfWeek dayOfWeek);

    DoctorTimetable findByDoctorIdAndDayOfWeek(Long id, DayOfWeek dayOfWeek);
}
