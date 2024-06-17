package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.dto.user.doctor.timetable.DoctorTimetableDto;
import com.blueTeam.medicalService.entities.DoctorTimetable;
import com.blueTeam.medicalService.mapper.DoctorTimetableMapping;
import com.blueTeam.medicalService.mapper.DoctorTimetableMappingImpl;
import com.blueTeam.medicalService.repositories.DoctorTimetableRepository;
import com.blueTeam.medicalService.services.interfaces.DoctorTimetableService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

//просмотр расписания всех врачей на заданный день
@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorTimetableServiceImpl implements DoctorTimetableService {
       private final DoctorTimetableRepository doctorTimetableRepository;
       private final DoctorTimetableMapping doctorTimetableMapping;

    @Override
    public List<DoctorTimetableDto> findDoctorTimetablesByDayOfWeek(DayOfWeek dayOfWeek) {
        List<DoctorTimetableDto> timetables = doctorTimetableRepository.findDoctorsTimetableByDayOfWeek(dayOfWeek).stream()
                .map(doctorTimetableMapping::mapToDto)
                .collect(Collectors.toList());
        return timetables;
    }
}
