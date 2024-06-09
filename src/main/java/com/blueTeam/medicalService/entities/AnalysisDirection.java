package com.blueTeam.medicalService.entities;

import com.blueTeam.medicalService.entities.enums.DirectionStatus;
import com.blueTeam.medicalService.entities.enums.Usage;
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

    @Enumerated
    private DirectionStatus status;

    @Enumerated
    private Usage usage;

    private String result;
}
