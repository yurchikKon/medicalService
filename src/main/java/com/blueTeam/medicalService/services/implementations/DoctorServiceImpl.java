package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.dto.user.doctor.DoctorRepresentationDto;
import com.blueTeam.medicalService.entities.Doctor;
import com.blueTeam.medicalService.mapper.DoctorMapper;
import com.blueTeam.medicalService.repositories.DoctorRepository;
import com.blueTeam.medicalService.services.interfaces.DoctorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;


    @Override
    public DoctorRepresentationDto getDoctorInfoDto(Long doctorId) {

        Optional<Doctor> doctor = Optional.ofNullable(doctorRepository.findById(doctorId)
                .orElseThrow(() -> new EntityNotFoundException("Doctor with id = " + doctorId + " not found")));
        return doctorMapper.mapToDto(doctor.get());

    }
}
