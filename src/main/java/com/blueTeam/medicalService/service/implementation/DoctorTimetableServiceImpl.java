package com.blueTeam.medicalService.service.implementation;

import com.blueTeam.medicalService.dto.user.doctor.timetable.DoctorTimetableDto;
import com.blueTeam.medicalService.entity.DoctorTimetable;
import com.blueTeam.medicalService.mapper.DoctorTimetableMapping;
import com.blueTeam.medicalService.mapper.DoctorTimetableMappingImpl;
import com.blueTeam.medicalService.repository.DoctorTimetableRepository;
import com.blueTeam.medicalService.service.interfaces.DoctorTimetableService;
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
        if (isWeekend(dayOfWeek)) {
            String errorMessage = "Выбран недопустимый день недели: " + dayOfWeek.toString();
            log.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        return doctorTimetableRepository.findDoctorsTimetableByDayOfWeek(dayOfWeek)
                .stream()
                .map(doctorTimetableMapping::mapToDto)
                .toList();
    }

    public boolean isWeekend(DayOfWeek dayOfWeek) {
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}
