package com.blueTeam.medicalService.services.interfaces;


import com.blueTeam.medicalService.dto.user.doctor.timetable.DoctorTimetableDto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public interface DoctorTimetableService {
    List<DoctorTimetableDto> findDoctorTimetablesByDayOfWeek(DayOfWeek dayOfWeek);
}
