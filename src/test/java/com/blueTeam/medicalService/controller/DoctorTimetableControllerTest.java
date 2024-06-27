package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.config.JwtService;
import com.blueTeam.medicalService.dto.user.doctor.DoctorRepresentationDto;
import com.blueTeam.medicalService.dto.user.doctor.timetable.DoctorTimetableDto;
import com.blueTeam.medicalService.service.implementation.DoctorTimetableServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DayOfWeek;
import java.time.LocalTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Disabled
@WebMvcTest(DoctorTimetableController.class)
class DoctorTimetableControllerMvcTest {

    private final String doctorTimetableControllerUrl = "/api/v1/doctorTimetables/date/{date}";

    @MockBean
    private DoctorTimetableServiceImpl doctorTimetableService;
    @MockBean
    private JwtService jwtService;
    @MockBean
    private UserDetailsService userDetailsService;

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    DoctorRepresentationDto doctorMock = mock(DoctorRepresentationDto.class);


    @Test
    @SneakyThrows
    void testGetDoctorTimetablesByDayOfWeek_Returns200()  {
        DoctorTimetableDto dto = DoctorTimetableDto.builder()
                .id(1L)
                .doctor(doctorMock)
                .dayOfWeek(DayOfWeek.MONDAY)
                .timeStart(LocalTime.of(9, 0))
                .timeEnd(LocalTime.of(10, 0))
                .roomNumber(101)
                .build();

        mockMvc.perform(get(doctorTimetableControllerUrl, "2023-06-24")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                        .andExpect(status().isOk());

        verify(doctorTimetableService.findDoctorTimetablesByDayOfWeek(any()));
    }
}
