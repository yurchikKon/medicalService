package com.blueTeam.medicalService.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "special_doctor_direction")
public class SpecialDoctorDirection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "appointment_doctor_id", referencedColumnName = "id")
    private DoctorsAppointment doctorsAppointment;

    @OneToOne
    @JoinColumn(name = "specialization_id", referencedColumnName = "id")
    private Specialization specialization;
}
