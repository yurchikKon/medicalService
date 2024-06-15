package com.blueTeam.medicalService.services.interfaces;


import com.blueTeam.medicalService.dto.user.doctor.DoctorRepresentationDto;

public interface DoctorService {

    DoctorRepresentationDto getDoctorInfoDto(Long doctorId);

}
