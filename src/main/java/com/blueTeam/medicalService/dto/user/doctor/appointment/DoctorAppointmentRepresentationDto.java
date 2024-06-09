package com.blueTeam.medicalService.dto.user.doctor.appointment;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.dto.medicalRecipient.MedicalRecipientRepresentationDto;
import com.blueTeam.medicalService.dto.payment.PayReceiptDto;
import com.blueTeam.medicalService.dto.receipt.MedicalReceiptDto;
import com.blueTeam.medicalService.dto.user.doctor.DoctorRepresentationDto;
import com.blueTeam.medicalService.dto.user.patient.PatientRepresentationDto;
import com.blueTeam.medicalService.entities.*;
import com.blueTeam.medicalService.entities.enums.Status;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Value
@Builder
public class DoctorAppointmentRepresentationDto {
    Long id;
    DoctorRepresentationDto doctorDto;
    PatientRepresentationDto patientDto;
    LocalDateTime dateTime;
    Status status;
    Set<MedicalRecipientRepresentationDto> medicalRecipientDtos;
    DoctorRepresentationDto doctorRemarkDto;
    List<AnalysisDirectionDto> analysisDirectionDtos;
    List<MedicalReceiptDto> medicalReceiptDtos;
    AppointmentReview appointmentReview;
    List<PayReceiptDto> payReceiptDtos;
}
