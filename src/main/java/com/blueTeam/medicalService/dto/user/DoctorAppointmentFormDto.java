package com.blueTeam.medicalService.dto.user;

import com.blueTeam.medicalService.dto.analysis.AnalysisDirectionDto;
import com.blueTeam.medicalService.dto.diagnosis.PatientDiagnosisDto;
import com.blueTeam.medicalService.dto.medicalRecipient.MedicalProcedureDto;
import com.blueTeam.medicalService.dto.payment.PayReceiptDto;
import com.blueTeam.medicalService.dto.receipt.MedicalReceiptDto;
import com.blueTeam.medicalService.dto.user.doctor.SpecialDoctorDirectionDto;
import com.blueTeam.medicalService.dto.user.doctor.remark.DoctorRemarkDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class DoctorAppointmentFormDto {

    Long id;
    Set<MedicalProcedureDto> medicalServicesDto;
    List<SpecialDoctorDirectionDto> directions;
    DoctorRemarkDto doctorRemarkDto;
    List<AnalysisDirectionDto> analysisDirectionDto;
    List<MedicalReceiptDto> medicalReceiptDto;
    List<PatientDiagnosisDto> patientsDiagnosisListDto;
    List<PayReceiptDto> payReceipts;

}
