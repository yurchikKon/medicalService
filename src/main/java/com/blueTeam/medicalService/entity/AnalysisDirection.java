package com.blueTeam.medicalService.entity;

import com.blueTeam.medicalService.entity.enums.DirectionStatus;
import com.blueTeam.medicalService.entity.enums.Usage;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "analysis_direction")
public class AnalysisDirection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_doctor_id", referencedColumnName = "id")
    private DoctorAppointment doctorsAppointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analysis_id", referencedColumnName = "id")
    private Analysis analysis;

    @Enumerated(EnumType.STRING)
    private DirectionStatus status;

    @Enumerated(EnumType.STRING)
    private Usage usage;

    private String result;
}
