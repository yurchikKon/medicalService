package com.blueTeam.medicalService.repository;


import com.blueTeam.medicalService.entity.AnalysisDirection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


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
