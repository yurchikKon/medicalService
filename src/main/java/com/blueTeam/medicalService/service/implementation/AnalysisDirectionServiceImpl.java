package com.blueTeam.medicalService.service.implementation;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionNamedDto;
import com.blueTeam.medicalService.entity.AnalysisDirection;
import com.blueTeam.medicalService.exception.InvalidStateException;
import com.blueTeam.medicalService.exception.ResourceAlreadyExistException;
import com.blueTeam.medicalService.mapper.AnalysisDirectionMapper;
import com.blueTeam.medicalService.mapper.AnalysisDirectionNamedMapper;
import com.blueTeam.medicalService.repository.AnalysisDirectionRepository;
import com.blueTeam.medicalService.service.AnalysisDirectionService;
import com.blueTeam.medicalService.service.PatientService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

import static com.blueTeam.medicalService.entity.enums.DirectionStatus.INVALID;
import static com.blueTeam.medicalService.entity.enums.DirectionStatus.VALID;
import static com.blueTeam.medicalService.entity.enums.Usage.USED;

@Service
@RequiredArgsConstructor
@Slf4j
public class AnalysisDirectionServiceImpl implements AnalysisDirectionService {
    private final AnalysisDirectionRepository analysisDirectionRepository;
    private final AnalysisDirectionMapper analysisDirectionMapper;
    private final AnalysisDirectionNamedMapper analysisDirectionNamedMapper;
    private final PatientService patientService;

    @Override
    @Transactional
    public AnalysisDirectionDto passAnalysis(Long id) {
        AnalysisDirection analysisDirection = analysisDirectionRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("No direction with such id "));
        if (analysisDirection.getStatus().equals(VALID)) {
            analysisDirection.setStatus(INVALID);
            analysisDirection.setUsage(USED);
            analysisDirectionRepository.save(analysisDirection);
            log.info("Analysis direction with id {} was used", id);

            return analysisDirectionMapper.mapToDto(analysisDirection);
        } else {
            throw new ResourceAlreadyExistException("Analysis has already been passed");
        }
    }

    @Override
    public AnalysisDirectionDto changeResultsAnalysisDirection(Long idAnalysisDirection, String newResult) {
        AnalysisDirection analysisDirection = analysisDirectionRepository.findById(idAnalysisDirection)
                .orElseThrow(() -> new EntityNotFoundException("Invalid id: " + idAnalysisDirection));
            if(analysisDirection.getUsage().equals(USED)){
                analysisDirection.setResult(newResult);
                analysisDirectionRepository.save(analysisDirection);
                log.info("Изменена запись в результатах анализа на: {}", newResult);

                return analysisDirectionMapper.mapToDto(analysisDirection);
            }
            else {
                throw new InvalidStateException("Analysis has not been passed yet");
            }
        }

    @Override
    @Transactional(readOnly = true)
    public List<AnalysisDirectionNamedDto> getUsedAnalysisRecords(Long id) {
        if (!patientService.isPatientPresent(id)) {
            throw new EntityNotFoundException("Invalid patient id: " + id);
        }
        var testAppointments = analysisDirectionRepository.findUnusedAnalysisByPatientId(id, USED);
        return testAppointments == null
                ? Collections.emptyList()
                : testAppointments.stream().map(t -> analysisDirectionNamedMapper.mapToNamedDto(t, t.getAnalysis())).toList();
    }
}
