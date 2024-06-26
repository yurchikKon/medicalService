package com.blueTeam.medicalService.service.implementation;

import com.blueTeam.medicalService.dto.payment.PayReceiptDto;
import com.blueTeam.medicalService.entity.Analysis;
import com.blueTeam.medicalService.entity.AnalysisDirection;
import com.blueTeam.medicalService.entity.DoctorAppointment;
import com.blueTeam.medicalService.entity.PayReceipt;
import com.blueTeam.medicalService.entity.enums.*;
import com.blueTeam.medicalService.exception.InvalidStateException;
import com.blueTeam.medicalService.mapper.PayReceiptMapper;
import com.blueTeam.medicalService.repository.AnalysisDirectionRepository;
import com.blueTeam.medicalService.repository.DoctorAppointmentRepository;
import com.blueTeam.medicalService.repository.PatientRepository;
import com.blueTeam.medicalService.repository.PayReceiptRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static com.blueTeam.medicalService.entity.enums.PaymentMethod.CARD;
import static com.blueTeam.medicalService.entity.enums.PaymentType.ANALYSIS;
import static com.blueTeam.medicalService.entity.enums.ReceiptStatus.PENDING;
import static com.blueTeam.medicalService.entity.enums.Status.COMPLETED;
import static com.blueTeam.medicalService.entity.enums.Usage.UNUSED;
import static com.blueTeam.medicalService.entity.enums.Usage.USED;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PayReceiptServiceImplTest {
    private static final PayReceiptDto PAY_RECEIPT_ANALYSIS_DTO = createPayReceiptDto(1L, ANALYSIS);
    private static final Long ANALYSIS_DIRECTION_ID = 1L;
    private static final AnalysisDirection ANALYSIS_DIRECTION = createAnalysisDirection(USED);
    private static final AnalysisDirection ANALYSIS_DIRECTION_UNUSED = createAnalysisDirection(UNUSED);
    private static final PayReceipt PAY_RECEIPT = createPayReceipt();
    private static final Long PATIENT_ID = 1L;
    private static final DoctorAppointment DOCTOR_APPOINTMENT_COMPLETED = createAppointment(COMPLETED);

    @InjectMocks
    PayReceiptServiceImpl payReceiptService;

    @Mock
    AnalysisDirectionRepository analysisDirectionRepository;

    @Mock
    PayReceiptRepository payReceiptRepository;

    @Mock
    PayReceiptMapper payReceiptMapper;

    @Mock
    PatientRepository patientRepository;

    @Mock
    DoctorAppointmentRepository doctorAppointmentRepository;

    @Test
    void createAnalysisPayReceipt_analysisDirectionFound() {
        when(analysisDirectionRepository.findById(ANALYSIS_DIRECTION_ID)).thenReturn(of(ANALYSIS_DIRECTION));
        when(payReceiptMapper.mapToDto(PAY_RECEIPT)).thenReturn(PAY_RECEIPT_ANALYSIS_DTO);

        assertEquals(PAY_RECEIPT_ANALYSIS_DTO, payReceiptService.createAnalysisPayReceipt(ANALYSIS_DIRECTION_ID));
    }

    @Test
    void createAnalysisPayReceipt_analysis_directionNotFound() {
        when(analysisDirectionRepository.findById(ANALYSIS_DIRECTION_ID)).thenReturn(empty());

        assertThrows(EntityNotFoundException.class,
                () -> payReceiptService.createAnalysisPayReceipt(ANALYSIS_DIRECTION_ID));
    }

    @Test
    void createAnalysisPayReceipt_unusedAnalysisDirection() {
        when(analysisDirectionRepository.findById(ANALYSIS_DIRECTION_ID)).thenReturn(of(ANALYSIS_DIRECTION_UNUSED));

        assertThrows(InvalidStateException.class,
                () -> payReceiptService.createAnalysisPayReceipt(ANALYSIS_DIRECTION_ID));
    }

    @Test
    void findAllByPatientId_patientExist() {
        when(patientRepository.existsById(PATIENT_ID)).thenReturn(true);
        when(doctorAppointmentRepository.findAllByPatientIdAndStatus(PATIENT_ID, COMPLETED))
                .thenReturn(List.of(DOCTOR_APPOINTMENT_COMPLETED));
        when(payReceiptMapper.mapToDto(PAY_RECEIPT)).thenReturn(PAY_RECEIPT_ANALYSIS_DTO);

        assertEquals(List.of(PAY_RECEIPT_ANALYSIS_DTO), payReceiptService.findAllByPatientId(PATIENT_ID));
    }

    @Test
    void findAllByPatientId_patientNotExist() {
        when(patientRepository.existsById(PATIENT_ID)).thenReturn(false);

        assertThrows(EntityNotFoundException.class, () -> payReceiptService.findAllByPatientId(PATIENT_ID));
    }

    private static DoctorAppointment createAppointment(Status status) {
        return DoctorAppointment.builder()
                .status(status)
                .payReceipts(List.of(createPayReceipt()))
                .build();
    }

    private static PayReceiptDto createPayReceiptDto(Long id, PaymentType type) {
        return PayReceiptDto.builder()
                .id(id)
                .paymentType(type)
                .build();
    }

    private static AnalysisDirection createAnalysisDirection(Usage usage) {
        return AnalysisDirection.builder()
                .usage(usage)
                .doctorsAppointment(new DoctorAppointment())
                .analysis(new Analysis(1L, "analysis", new BigDecimal(1500), null))
                .build();
    }

    private static PayReceipt createPayReceipt() {
        PayReceipt payReceipt = new PayReceipt();
        payReceipt.setStatus(PENDING);
        payReceipt.setDoctorsAppointment(new DoctorAppointment());
        payReceipt.setPaymentType(ANALYSIS);
        payReceipt.setValue(new BigDecimal(1500));
        payReceipt.setPaymentMethod(CARD);

        return payReceipt;
    }
}