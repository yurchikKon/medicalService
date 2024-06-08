package com.blueTeam.medicalService.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "doctor_remark")
public class DoctorsRemark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "appointment_doctor_id", referencedColumnName = "id")
    private DoctorsAppointment doctorsAppointment;
}
