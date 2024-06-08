package com.blueTeam.medicalService.models;

import com.blueTeam.medicalService.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "patient")
public class Patient extends User{

    private Gender gender;

    @Column(name = "birth_date")
    private String birthDate;

    @OneToMany(mappedBy = "patient")
    private List<DoctorsAppointment> doctorsAppointments;
}
