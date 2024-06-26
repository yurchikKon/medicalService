package com.blueTeam.medicalService.repository;

import com.blueTeam.medicalService.entity.AnalysisDirection;
import com.blueTeam.medicalService.entity.enums.Usage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalysisDirectionRepository extends JpaRepository<AnalysisDirection, Long> {

    @Query("SELECT ad FROM AnalysisDirection ad JOIN ad.doctorsAppointment app JOIN app.patient u  WHERE u.id = :patientId AND ad.usage = :usage")
    List<AnalysisDirection> findUnusedAnalysisByPatientId(@Param("patientId") Long patientId, @Param("usage") Usage usage);

}
