package com.blueTeam.medicalService.security.auth;

import com.blueTeam.medicalService.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String login;
    private String password;
    private String email;
}
