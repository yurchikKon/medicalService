package com.blueTeam.medicalService.service.implementation;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.dto.user.doctor.appointment.DoctorAppointmentRepresentationDto;
import com.blueTeam.medicalService.entity.AnalysisDirection;
import com.blueTeam.medicalService.entity.DoctorAppointment;
import com.blueTeam.medicalService.entity.Patient;
import com.blueTeam.medicalService.entity.enums.Status;
import com.blueTeam.medicalService.mapper.AnalysisDirectionMapper;
import com.blueTeam.medicalService.mapper.DoctorAppointmentMapper;
import com.blueTeam.medicalService.repository.DoctorAppointmentRepository;
import com.blueTeam.medicalService.repository.PatientRepository;
import com.blueTeam.medicalService.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.blueTeam.medicalService.entity.enums.DirectionStatus.VALID;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatientServiceImpl implements PatientService {
    private final DoctorAppointmentRepository doctorAppointmentRepository;
    private final AnalysisDirectionMapper analysisDirectionMapper;
    private final DoctorAppointmentMapper doctorAppointmentMapper;
    private final PatientRepository patientRepository;

    @Override
    public List<AnalysisDirectionDto> findActivePatientAnalysisDirections(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("No patient with such id"));
        if (patient.getDoctorsAppointments() != null) {
            return patient.getDoctorsAppointments()
                    .stream()
                    .map(DoctorAppointment::getAnalysisDirections)
                    .filter(list -> !list.isEmpty())
                    .flatMap(List::stream)
                    .filter(dir -> dir.getStatus().equals(VALID))
                    .map(analysisDirectionMapper::mapToDto)
                    .toList();
        }
        else {
            throw new EntityNotFoundException("No analysis directions were found");
        }
    }

    @Override
    public List<DoctorAppointmentRepresentationDto> findActivePatientAppointments(Long patientId) {
        List<DoctorAppointment> appointments =
                doctorAppointmentRepository.findAllByPatientIdAndStatus(patientId, Status.SCHEDULED);
        log.info("Found: {} appointments", appointments.size());

        return appointments.stream()
                .map(doctorAppointmentMapper::mapToDto)
                .toList();
    }

}
