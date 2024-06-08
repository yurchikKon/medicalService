package com.blueTeam.medicalService.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Table(name = "medical_receipt")
public class MedicalReceipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_doctor_id", referencedColumnName = "id")
    private DoctorsAppointment doctorsAppointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "preparation_id", referencedColumnName = "id")
    private Preparation preparation;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    }
