package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.AnalysisDirection;
import com.blueTeam.medicalService.entities.enums.Usage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnalysisDirectionRepository extends JpaRepository<AnalysisDirection, Long> {
    @Query(
            nativeQuery = true,
            value = "select users.first_name, users.last_name, users.phone_number, analysis_list.\"name\", analysis_direction.\"usage\", analysis_direction.\"result\" \n" +
                    "from  analysis_direction\n" +
                    "join analysis_list on analysis_direction.analysis_id = analysis_list.id \n" +
                    "join appointment_doctor on analysis_direction.appointment_doctor_id = appointment_doctor.id\n" +
                    "join  users on appointment_doctor.user_id = users.id \n" +
                    "where  analysis_direction.status = 'valid' and analysis_direction.\"usage\" = ?1  and   users.\"id\" = ?2"
    )
    List<AnalysisDirection> findAllByPatientidAndUsage( Usage usage, Long patientId);
}
