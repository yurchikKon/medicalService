package com.blueTeam.medicalService.entities;

import com.blueTeam.medicalService.entities.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "appointment_doctor")
public class DoctorAppointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", referencedColumnName = "id")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Patient patient;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @Enumerated
    private Status status;

    @ManyToMany
    @JoinTable(
            name = "appointment_service",
            joinColumns = @JoinColumn(name = "appointment_doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "medical_service_id"))
    private Set<MedicalProcedure> medicalServices;

    @OneToMany(mappedBy = "doctorsAppointment")
    private List<SpecialDoctorDirection> directions;

    @OneToOne(mappedBy = "doctorsAppointment", fetch = FetchType.LAZY)
    private DoctorRemark doctorsRemark;

    @OneToMany(mappedBy = "doctorsAppointment")
    private List<AnalysisDirection> analysisDirections;

    @OneToMany(mappedBy = "doctorsAppointment")
    private List<MedicalReceipt> medicalReceipts;

    @OneToOne(mappedBy = "doctorsAppointment", fetch = FetchType.LAZY)
    private AppointmentReview appointmentReview;

    @OneToMany(mappedBy = "doctorsAppointment")
    private List<PatientDiagnosis> patientsDiagnosisList;

    @OneToMany(mappedBy = "doctorsAppointment")
    private List<PayReceipt> payReceipts;
}
