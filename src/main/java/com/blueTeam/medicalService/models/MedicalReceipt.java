package com.blueTeam.medicalService.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.access.method.P;

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

    @ManyToOne
    @JoinColumn(name = "appointment_doctor_id", referencedColumnName = "id")
    private DoctorsAppointment doctorsAppointment;

    @OneToOne
    @JoinColumn(name = "preparation_id", referencedColumnName = "id")
    private Preparation preparation;

    @Column(name = "date_end")
    private LocalDate dateEnd;

    }
