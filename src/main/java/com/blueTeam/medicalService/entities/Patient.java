package com.blueTeam.medicalService.entities;

import com.blueTeam.medicalService.entities.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient extends User{

    private Gender gender;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @OneToMany(mappedBy = "patient")
    private List<DoctorsAppointment> doctorsAppointments;
}
