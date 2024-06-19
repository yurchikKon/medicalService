package com.blueTeam.medicalService.service.interfaces;


import com.blueTeam.medicalService.dto.user.doctor.DoctorRepresentationDto;

public interface DoctorService {

    DoctorRepresentationDto getDoctorInfoDto(Long doctorId);

}
