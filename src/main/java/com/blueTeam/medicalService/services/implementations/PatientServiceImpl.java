package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.entities.AnalysisDirection;
import com.blueTeam.medicalService.entities.DoctorAppointment;
import com.blueTeam.medicalService.entities.enums.DirectionStatus;
import com.blueTeam.medicalService.entities.enums.Status;
import com.blueTeam.medicalService.mapper.AnalysisDirectionMapper;
import com.blueTeam.medicalService.mapper.DoctorAppointmentMapper;
import com.blueTeam.medicalService.repositories.DoctorAppointmentRepository;
import com.blueTeam.medicalService.services.interfaces.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatientServiceImpl implements PatientService {
    private final DoctorAppointmentRepository doctorAppointmentRepository;
    private final AnalysisDirectionMapper mapper;
    private final DoctorAppointmentMapper doctorAppointmentMapper;

    @Override
    public List<AnalysisDirectionDto> findActivePatientAnalysisDirections(Long patientId) {
        List<DoctorAppointment> appointmentList = doctorAppointmentRepository.findAllByPatientIdAndStatus(patientId, Status.COMPLETED);
        log.info("Found: {} appointments", appointmentList.size());

        List<AnalysisDirection> directionList= appointmentList.stream()
                .map(DoctorAppointment::getAnalysisDirections)
                .filter(list -> !list.isEmpty())
                .flatMap(List::stream)
                .filter(direction -> direction.getStatus()== DirectionStatus.VALID)
                .toList();
        log.info("Found: {} active directions", directionList.size());

        return directionList.stream().map(mapper::mapToDto).toList();
    }

    @Override
    public List<DoctorAppointmentRepresentationDto> findActivePatientAppointments(Long patientId) {
        List<DoctorAppointment> appointments =
                doctorAppointmentRepository.findAllByPatientIdAndStatus(patientId, Status.SCHEDULED);
        log.info("Found: {} appointments",appointments.size());

        return appointments.stream()
                .map(doctorAppointmentMapper::mapToDto)
                .collect(Collectors.toList());
    }

}
