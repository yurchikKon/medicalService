package com.blueTeam.medicalService.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
@AllArgsConstructor
@Table(name = "preparation_list")
public class Preparation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "preparation", fetch = FetchType.LAZY)
    private List<MedicalReceipt> medicalReceipt;

}
