package com.blueTeam.medicalService.service;


import com.blueTeam.medicalService.dto.fda.DrugDto;

public interface DrugService {
  DrugDto getDrugInfo(String name);
}
