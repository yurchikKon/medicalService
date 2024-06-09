package com.blueTeam.medicalService.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "doctor")
public class Doctor extends User {

    @ManyToOne
    @JoinColumn(name = "specialization_id", referencedColumnName = "id")
    private Specialization specialization;

    private Double rate;

    @OneToMany(mappedBy = "doctor")
    private List<DoctorTimetable> timetable;

    @OneToMany(mappedBy = "doctor")
    private List<DoctorAppointment> doctorsAppointments;

}
