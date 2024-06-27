package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.dto.user.doctor.timetable.DoctorTimetableDto;
import com.blueTeam.medicalService.entity.DoctorTimetable;
import com.blueTeam.medicalService.service.DoctorTimetableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/doctor-timetables")
@Slf4j
public class DoctorTimetableController {
    private final DoctorTimetableService doctorTimetableService;

    @GetMapping("/date/{date}")
    public ResponseEntity<List<DoctorTimetableDto>> getDoctorTimetablesByDayOfWeek(
            @PathVariable("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return ResponseEntity.ok(doctorTimetableService.findDoctorTimetablesByDayOfWeek(dayOfWeek));
    }
}
