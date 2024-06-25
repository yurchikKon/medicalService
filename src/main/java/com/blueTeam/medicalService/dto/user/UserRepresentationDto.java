package com.blueTeam.medicalService.dto.user;

import com.blueTeam.medicalService.entity.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import static lombok.AccessLevel.PRIVATE;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
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
