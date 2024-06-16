package com.blueTeam.medicalService.controllers;

import com.blueTeam.medicalService.dto.user.patient.PatientCreateEditDto;
import com.blueTeam.medicalService.dto.security.AuthenticationRequest;
import com.blueTeam.medicalService.dto.security.AuthenticationResponse;
import com.blueTeam.medicalService.services.implementations.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/security")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;


    @PostMapping("/registration/patient")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody PatientCreateEditDto dto) {
        return ResponseEntity.ok(authenticationService.registerPatient(dto));
    }
    @PostMapping("/authentication")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
