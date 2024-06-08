package com.blueTeam.medicalService.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "analysis_list")
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private BigDecimal cost;

    @OneToMany(mappedBy = "analysis")
    private List<AnalysisDirection> analysisDirection;
}
