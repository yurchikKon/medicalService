package com.blueTeam.medicalService.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "specialization_list")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "specialization")
    private List<Doctor> doctors;

    @OneToOne(mappedBy = "specialization")
    private SpecialDoctorDirection direction;
}
