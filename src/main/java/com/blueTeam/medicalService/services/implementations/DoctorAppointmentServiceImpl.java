package com.blueTeam.medicalService.services.implementations;


import com.blueTeam.medicalService.dto.user.doctor.DoctorRepresentationDto;
import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.dto.user.patient.PatientRepresentationDto;
import com.blueTeam.medicalService.entities.Doctor;
import com.blueTeam.medicalService.entities.DoctorAppointment;
import com.blueTeam.medicalService.entities.Patient;
import com.blueTeam.medicalService.repositories.DoctorAppointmentRepository;
import com.blueTeam.medicalService.services.interfaces.DoctorAppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorAppointmentServiceImpl implements DoctorAppointmentService {
    private final DoctorAppointmentRepository doctorAppointmentRepository;

    public List<DoctorAppointment> getDoctorAppointmentByDateTime(LocalDateTime dateTime) {
        if (dateTime.isAfter(LocalDateTime.now().plusWeeks(3))) {
            log.error("Дата слишком далеко в будущем: {}", dateTime);
            throw new IllegalArgumentException("Дата слишком далеко в будущем");
        }
        return doctorAppointmentRepository.findAllDoctorsByDateTime(dateTime);
    }
    public List<DoctorAppointmentRepresentationDto> getDoctorAppointmentRepresentationDto(LocalDateTime dateTime) {
        log.info("Преобразование списка приемов в DTO в методе getDoctorAppointmentRepresentationDto");
        return convertAppointmentListToDto(this.getDoctorAppointmentByDateTime(dateTime));
    }

    public List<DoctorAppointmentRepresentationDto> convertAppointmentListToDto(List<DoctorAppointment> appointments) {
        log.info("Преобразование списка приемов в DTO в методе convertAppointmentListToDto");
        return appointments.stream().map(appointment -> {
            Doctor doctor = appointment.getDoctor();
            Patient patient = appointment.getPatient();
            return DoctorAppointmentRepresentationDto.builder()
                    .dateTime(appointment.getDateTime())
                    .status(appointment.getStatus())
                    .doctorDto(DoctorRepresentationDto.builder()
                            .id(doctor.getId())
                            .firstName(doctor.getFirstName())
                            .lastName(doctor.getLastName())
                            .build())
                    .patientDto(PatientRepresentationDto.builder()
                            .id(patient.getId())
                            .firstName(patient.getFirstName())
                            .lastName(patient.getLastName())
                            .build())
                    .build();
        }).collect(Collectors.toList());
    }
}

