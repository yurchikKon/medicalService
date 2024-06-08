package com.blueTeam.medicalService.models;

import com.blueTeam.medicalService.enums.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "appointment_doctor")
public class DoctorsAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Patient patient;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    private Status status;

    @ManyToMany
    @JoinTable(
            name = "appointment_service",
            joinColumns = @JoinColumn(name = "appointment_doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "medical_service_id"))
    private List<MedicalService> medicalServices;

    @OneToMany(mappedBy = "doctorsAppointment")
    private List<SpecialDoctorDirection> directions;

    @OneToOne(mappedBy = "appointment_doctor")
    private DoctorsRemark doctorsRemark;

    @OneToMany(mappedBy = "doctorsAppointment")
    private List<AnalysisDirection> analysisDirections;

    @OneToMany(mappedBy = "doctorsAppointment")
    private List<MedicalReceipt> medicalReceipts;
}
