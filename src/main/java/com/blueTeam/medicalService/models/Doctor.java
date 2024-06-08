package com.blueTeam.medicalService.models;

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

    private double rate;

    @OneToOne(mappedBy = "doctor")
    private DoctorTimetable timetable;

    @OneToMany(mappedBy = "doctor")
    private List<DoctorsAppointment> doctorsAppointments;

}
