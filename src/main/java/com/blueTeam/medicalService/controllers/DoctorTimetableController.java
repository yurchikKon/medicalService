package com.blueTeam.medicalService.controllers;

import com.blueTeam.medicalService.dto.user.doctor.timetable.DoctorTimetableDto;
import com.blueTeam.medicalService.entities.DoctorTimetable;
import com.blueTeam.medicalService.services.interfaces.DoctorTimetableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/doctorTimetables")
@Slf4j
public class DoctorTimetableController {
    private final DoctorTimetableService doctorTimetableService;
    @GetMapping
    public ResponseEntity<List<DoctorTimetableDto>> getDoctorTimetablesByDayOfWeek(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            String errorMessage = "Выбран недопустимый день недели: " + dayOfWeek.toString();
            log.error(errorMessage);
            return ResponseEntity.badRequest().body(null);
        }
        List<DoctorTimetableDto> timetables = doctorTimetableService.findDoctorTimetablesByDayOfWeek(dayOfWeek);
        return ResponseEntity.ok(timetables);
    }
}
