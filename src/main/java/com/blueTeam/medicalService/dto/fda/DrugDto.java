package com.blueTeam.medicalService.dto.fda;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record DrugDto(
        String name,
        String disclaimer,
        String productElements,
        String activeIngredient,
        String purpose,
        String warnings
) {
}
