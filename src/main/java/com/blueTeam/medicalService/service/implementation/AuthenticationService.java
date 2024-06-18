package com.blueTeam.medicalService.service.implementation;

import com.blueTeam.medicalService.dto.user.patient.PatientCreateEditDto;
import com.blueTeam.medicalService.entity.Patient;
import com.blueTeam.medicalService.entity.User;
import com.blueTeam.medicalService.mapper.PatientMapper;
import com.blueTeam.medicalService.repository.PatientRepository;
import com.blueTeam.medicalService.repository.UserRepository;
import com.blueTeam.medicalService.config.JwtService;
import com.blueTeam.medicalService.dto.security.AuthenticationRequest;
import com.blueTeam.medicalService.dto.security.AuthenticationResponse;
import lombok.RequiredArgsConstructor;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final PatientRepository patientRepository;
    private final PatientMapper patientMapper;

    public AuthenticationResponse registerPatient (PatientCreateEditDto dto) {

        Patient patient = patientMapper.mapToEntity(dto);
        patientRepository.save(patient);
        String jwtToken = jwtService.generateToken(patient);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );
        User user = userRepository.findByLogin(request.getLogin()).orElseThrow();
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }
}
