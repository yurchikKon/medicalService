package com.blueTeam.medicalService.dto.user;

import com.blueTeam.medicalService.entities.enums.Role;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@FieldDefaults(level = PRIVATE)
public class UserRepresentationDto {
    Long id;
    String firstName;
    String lastName;
    Role role;
    String phoneNumber;
    String address;
    String login;
    String email;
}
