package com.blueTeam.medicalService.entities;

import com.blueTeam.medicalService.enums.DirectionStatus;
import com.blueTeam.medicalService.enums.Usage;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "analysis_direction")
public class AnalysisDirection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_doctor_id", referencedColumnName = "id")
    private DoctorsAppointment doctorsAppointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "analysis_id", referencedColumnName = "id")
    private Analysis analysis;

    private DirectionStatus status;

    private Usage usage;

    private String result;
}
