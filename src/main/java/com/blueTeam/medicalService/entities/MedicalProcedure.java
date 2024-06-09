package com.blueTeam.medicalService.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "medical_service")
public class MedicalProcedure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private BigDecimal cost;

    @ManyToMany(mappedBy = "medicalServices")
    private List<DoctorAppointment> doctorsAppointments;

}
