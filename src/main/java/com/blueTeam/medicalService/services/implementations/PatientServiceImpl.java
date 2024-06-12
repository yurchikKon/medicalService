package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.dto.user.patient.PatientActiveAppointmentDto;
import com.blueTeam.medicalService.entities.Doctor;
import com.blueTeam.medicalService.entities.DoctorAppointment;
import com.blueTeam.medicalService.entities.DoctorTimetable;
import com.blueTeam.medicalService.entities.enums.Status;
import com.blueTeam.medicalService.repositories.DoctorAppointmentRepository;
import com.blueTeam.medicalService.services.interfaces.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {

    private final DoctorAppointmentRepository doctorAppointmentRepository;

    public List<PatientActiveAppointmentDto> getActivePatientAppointmentDto(Long id) {
        List<DoctorAppointment>  appointments = doctorAppointmentRepository.findAllByPatientIdAndStatus(id, Status.SCHEDULED);
        return appointments.stream().map(appointment -> {
            Doctor doctor = appointment.getDoctor();
            return PatientActiveAppointmentDto.builder()
                    .firstName(doctor.getFirstName())
                    .lastName(doctor.getLastName())
                    .dateTime(appointment.getDateTime())
                    .specialization(doctor.getSpecialization().getName())
                    .roomNumber(doctor.getTimetable().stream().map(DoctorTimetable::getRoomNumber).findFirst().get())
                    .build();
        }).collect(Collectors.toList());
    }



}
