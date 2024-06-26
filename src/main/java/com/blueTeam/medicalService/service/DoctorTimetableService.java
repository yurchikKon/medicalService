package com.blueTeam.medicalService.service;


import com.blueTeam.medicalService.dto.user.doctor.timetable.DoctorTimetableDto;

import java.time.DayOfWeek;
import java.util.List;

public interface DoctorTimetableService {
    List<DoctorTimetableDto> findDoctorTimetablesByDayOfWeek(DayOfWeek dayOfWeek);
    boolean isWeekend(DayOfWeek dayOfWeek);
}
