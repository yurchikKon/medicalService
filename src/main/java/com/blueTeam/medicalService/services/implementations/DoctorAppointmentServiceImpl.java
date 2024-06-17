package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.dto.user.doctor.DoctorRepresentationDto;
import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.dto.user.patient.PatientRepresentationDto;
import com.blueTeam.medicalService.entities.Doctor;
import com.blueTeam.medicalService.entities.DoctorAppointment;
import com.blueTeam.medicalService.entities.Patient;
import com.blueTeam.medicalService.exceptions.DoctorAppointmentNotFoundException;
import com.blueTeam.medicalService.mapper.DoctorAppointmentMapper;
import com.blueTeam.medicalService.repositories.DoctorAppointmentRepository;
import com.blueTeam.medicalService.services.interfaces.DoctorAppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorAppointmentServiceImpl implements DoctorAppointmentService {
    private final DoctorAppointmentRepository doctorAppointmentRepository;
    private final DoctorAppointmentMapper doctorAppointmentMapper;

    public List<DoctorAppointmentRepresentationDto> getAllDoctorsAppointmentRepresentationDto(LocalDate localdate) {
        if (localdate.isAfter(LocalDate.now().plusWeeks(3))) {
            log.error("Дата слишком далеко в будущем: {}", localdate);
            throw new IllegalArgumentException("Дата слишком далеко в будущем");
        } else {
            try {
                List<DoctorAppointment> appointments = doctorAppointmentRepository.findAllByDateTime(localdate);
                return appointments.stream().map(doctorAppointmentMapper::mapToDto).collect(Collectors.toList());
            } catch (Exception e) {
                log.error("Ошибка при получении приемов у врача: {}", e.getMessage());
                throw new DoctorAppointmentNotFoundException("Ошибка при получении приемов у врача", e);
            }
        }
    }
}

