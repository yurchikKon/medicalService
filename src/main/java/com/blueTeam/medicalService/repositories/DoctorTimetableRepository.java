package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.Doctor;
import com.blueTeam.medicalService.entities.DoctorTimetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface DoctorTimetableRepository extends JpaRepository<DoctorTimetable, Long> {

    List<DoctorTimetable> findDoctorsTimetableByDayOfWeek(DayOfWeek dayOfWeek);
}
