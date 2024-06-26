package com.blueTeam.medicalService.service;

import com.blueTeam.medicalService.dto.user.doctor.timetable.DoctorTimetableDto;
import com.blueTeam.medicalService.mapper.DoctorTimetableMapping;
import com.blueTeam.medicalService.repository.DoctorTimetableRepository;
import com.blueTeam.medicalService.service.implementation.DoctorTimetableServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.DayOfWeek;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DoctorTimetableServiceImplTest {
    @Mock
    private DoctorTimetableRepository doctorTimetableRepository;

    @Mock
    private DoctorTimetableMapping doctorTimetableMapping;

    @InjectMocks
    private DoctorTimetableServiceImpl doctorTimetableService;


    @ParameterizedTest
    @ValueSource(strings = {"SATURDAY", "SUNDAY"})
    void testFindDoctorTimetablesByDayOfWeekInvalidDay(String dayOfWeekString) {
        DayOfWeek dayOfWeek = DayOfWeek.valueOf(dayOfWeekString);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            doctorTimetableService.findDoctorTimetablesByDayOfWeek(dayOfWeek);
        });

        assertEquals("Выбран недопустимый день недели: " + dayOfWeek.toString(), exception.getMessage());
    }
    @Test
    void testIsWeekendSaturday() {
        assertTrue(doctorTimetableService.isWeekend(DayOfWeek.SATURDAY));
    }

    @Test
    void testIsWeekendSunday() {
        assertTrue(doctorTimetableService.isWeekend(DayOfWeek.SUNDAY));
    }

    @Test
    void testIsWeekendWeekday() {
        assertFalse(doctorTimetableService.isWeekend(DayOfWeek.MONDAY));
    }
}
