package com.blueTeam.medicalService.dto.analysis;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record AnalysisRepresentationDto(
        Long id,
        String name,
        BigDecimal cost,
        List<AnalysisDirectionCreateEditDto> analysisDirectionDto
) {
}
