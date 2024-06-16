package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.entities.AnalysisDirection;
import com.blueTeam.medicalService.entities.enums.Usage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnalysisDirectionRepository extends JpaRepository<AnalysisDirection, Long> {
    @Query(value =  "select  ad  " +
                    "from AnalysisDirection ad " +
                    "join Analysis a " +
                    "on ad.analysis.id = a.id " +
                    "join DoctorAppointment da " +
                    "on ad.doctorsAppointment.id = da.id " +
                    "join Patient p " +
                    "on da.patient.id = p.id "+
                    "where ad.usage = 'USED' " +
                    "and p.id =:customerId"
    )
    List<AnalysisDirection> findAllByPatientidAndUsage(Long customerId);
}
