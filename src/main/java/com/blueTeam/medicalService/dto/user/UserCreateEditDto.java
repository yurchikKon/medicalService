package com.blueTeam.medicalService.dto.user;

import com.blueTeam.medicalService.entities.enums.Role;
import com.blueTeam.medicalService.validation.group.CreateAction;
import com.blueTeam.medicalService.validation.group.UpdateAction;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class UserCreateEditDto {

    @NotBlank(groups = {UpdateAction.class})
    Long id;

    @NotBlank
    @Size(min = 3, max = 30, groups = {CreateAction.class, UpdateAction.class})
    String firstName;

    @NotBlank
    @Size(min = 3, max = 30, groups = {CreateAction.class, UpdateAction.class})
    String lastName;

    @NotBlank
    Role role;

    @NotBlank
    @Pattern(
            regexp = "^[+]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}$",
            groups = {CreateAction.class, UpdateAction.class}
    )
    String phoneNumber;

    String address;

    @NotBlank
    @Size(min = 3, max = 255, groups = {CreateAction.class, UpdateAction.class})
    String login;

    @NotBlank
    String password;

    @NotBlank
    @Email(
            regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$",
            groups = {CreateAction.class, UpdateAction.class}
    )
    String email;
}
