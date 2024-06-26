package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.config.JwtService;
import com.blueTeam.medicalService.dto.user.doctor.DoctorRepresentationDto;
import com.blueTeam.medicalService.dto.user.patient.PatientRepresentationDto;
import com.blueTeam.medicalService.service.implementation.DoctorTimetableServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.servlet.MockMvc;



import static org.mockito.Mockito.mock;


@WebMvcTest(DoctorAppointmentController.class)
public class DoctorAppointmentControllerTest {

    private final String doctorAppointmentDateUrl = "/api/v1/doctorAppointments/date/{date}";

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
    PatientRepresentationDto patientMock = mock(PatientRepresentationDto.class);

//TODO

//    @Test
//    @SneakyThrows
//    public void getAllDoctorAppointmentsControllerTest(){
//        DoctorAppointmentRepresentationDto dto = DoctorAppointmentRepresentationDto.builder()
//                .id(1L)
//                .doctorDto(doctorMock)
//                .patientDto(patientMock)
//                .dateTime(LocalDateTime.of(LocalDate.of(2023, 6, 24), LocalTime.of(0, 0)))
//                .status(SCHEDULED)
//                .medicalRecipientDtos()
//        .specialDoctorDirectionDtos()
//        .doctorRemarkDto()
//        .analysisDirectionDtos()
//        .medicalReceiptDtos()
//        .appointmentReviewDto()
//        .payReceiptDtos()
//                .build();
//
//
//        mockMvc.perform(get(doctorAppointmentDateUrl, "2023-06-24")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(dto)))
//        .andExpect(status().isOk());
//    }

}
