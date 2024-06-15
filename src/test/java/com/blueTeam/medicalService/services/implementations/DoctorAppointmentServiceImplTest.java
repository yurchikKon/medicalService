package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.entities.Doctor;
import com.blueTeam.medicalService.entities.DoctorAppointment;
import com.blueTeam.medicalService.entities.Patient;
import com.blueTeam.medicalService.entities.Specialization;
import com.blueTeam.medicalService.entities.enums.Notification;
import com.blueTeam.medicalService.entities.enums.Status;
import com.blueTeam.medicalService.mapper.DoctorAppointmentMapper;
import com.blueTeam.medicalService.repositories.DoctorAppointmentRepository;
import com.blueTeam.medicalService.repositories.DoctorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DoctorAppointmentServiceImplTest {
    private static final Long DOCTOR_ID = 2L;
    private static final LocalDate DATE = LocalDate.now();
    private static final DoctorAppointment FIRST_APPOINTMENT = appointment(1L);
    private static final DoctorAppointment SECOND_APPOINTMENT = appointment(2L);
    private static final DoctorAppointmentRepresentationDto FIRST_DTO = appointmentRepresentationDto(1L);
    private static final DoctorAppointmentRepresentationDto SECOND_DTO = appointmentRepresentationDto(2L);

    @Mock
    DoctorRepository doctorRepository;
    @Mock
    DoctorAppointmentRepository doctorAppointmentRepository;
    @Mock
    DoctorAppointmentMapper doctorAppointmentMapper;
    @InjectMocks
    DoctorAppointmentServiceImpl doctorAppointmentService;

    @Test
    void findAllScheduledByDoctorIdAndDate() {
        when(doctorRepository.existsById(DOCTOR_ID)).thenReturn(true);
        when(doctorAppointmentRepository.findAllScheduledByDoctorIdAndDate(DOCTOR_ID, DATE))
            .thenReturn(List.of(FIRST_APPOINTMENT, SECOND_APPOINTMENT));
        when(doctorAppointmentMapper.mapToDto(FIRST_APPOINTMENT)).thenReturn(FIRST_DTO);
        when(doctorAppointmentMapper.mapToDto(SECOND_APPOINTMENT)).thenReturn(SECOND_DTO);

        List<DoctorAppointmentRepresentationDto> expected = List.of(FIRST_DTO, SECOND_DTO);
        List<DoctorAppointmentRepresentationDto> actual = doctorAppointmentService.findAllScheduledByDoctorIdAndDate(DOCTOR_ID, DATE);

        assertEquals(expected, actual);
    }

    @Test
    void findAllFreeAppointmentsByDoctorIdAndDate() {
    }

    @Test
    void createAppointment() {
    }

    @Test
    void cancelAppointment() {
    }

    private static DoctorAppointment appointment(Long id) {
        DoctorAppointment doctorAppointment = new DoctorAppointment();
        doctorAppointment.setId(id);
        return doctorAppointment;
    }

    private static DoctorAppointmentRepresentationDto appointmentRepresentationDto(Long id) {
        return DoctorAppointmentRepresentationDto.builder()
            .id(id)
            .build();
    }
}