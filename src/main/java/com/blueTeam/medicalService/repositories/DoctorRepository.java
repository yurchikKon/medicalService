package com.blueTeam.medicalService.repositories;

import com.blueTeam.medicalService.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Long, Doctor> {

    Optional<Doctor> findById(Long id);
}
