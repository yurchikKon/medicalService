package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.entities.DoctorAppointment;
import com.blueTeam.medicalService.entities.enums.Status;
import com.blueTeam.medicalService.mapper.DoctorAppointmentMapper;
import com.blueTeam.medicalService.repositories.DoctorAppointmentRepository;
import com.blueTeam.medicalService.services.interfaces.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final DoctorAppointmentMapper mapper;
    private final DoctorAppointmentRepository doctorAppointmentRepository;

    public List<DoctorAppointmentRepresentationDto> getActivePatientAppointmentDto(Long id) {

        List<DoctorAppointment> appointments =
                doctorAppointmentRepository.findAllByPatientIdAndStatus(id, Status.SCHEDULED);
        log.info("Found: {} appointments",appointments.size());
        return appointments.stream()
                .map(mapper::mapToDto)
                .collect(Collectors.toList());

    }
}
