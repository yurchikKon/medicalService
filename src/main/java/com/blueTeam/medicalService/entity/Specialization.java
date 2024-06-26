package com.blueTeam.medicalService.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "specialization_list")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "specialization")
    private List<Doctor> doctors;

    @OneToMany(mappedBy = "specialization")
    private List<SpecialDoctorDirection> direction;
}
