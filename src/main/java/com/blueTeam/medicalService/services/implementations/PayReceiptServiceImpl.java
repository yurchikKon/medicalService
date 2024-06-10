package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.dto.payment.PayReceiptDto;
import com.blueTeam.medicalService.entities.AnalysisDirection;
import com.blueTeam.medicalService.entities.DoctorAppointment;
import com.blueTeam.medicalService.entities.PayReceipt;
import com.blueTeam.medicalService.entities.enums.PaymentMethod;
import com.blueTeam.medicalService.entities.enums.PaymentType;
import com.blueTeam.medicalService.entities.enums.ReceiptStatus;
import com.blueTeam.medicalService.entities.enums.Status;
import com.blueTeam.medicalService.repositories.*;
import com.blueTeam.medicalService.services.interfaces.PayReceiptService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.blueTeam.medicalService.entities.enums.PaymentMethod.CARD;
import static com.blueTeam.medicalService.entities.enums.PaymentType.ANALYSIS;
import static com.blueTeam.medicalService.entities.enums.ReceiptStatus.PENDING;

@Service
@RequiredArgsConstructor
@Slf4j
public class PayReceiptServiceImpl implements PayReceiptService {
    private final AnalysisDirectionRepository analysisDirectionRepository;
    private final PayReceiptRepository payReceiptRepository;
    private final DoctorAppointmentRepository doctorAppointmentRepository;
    private final PatientRepository patientRepository;

    @Override
    public PayReceipt createAnalysisPayReceipt(Long analysisDirectionId) {
        AnalysisDirection analysisDirection = analysisDirectionRepository.findById(analysisDirectionId)
            .orElseThrow(() -> new EntityNotFoundException("no analysis direction with such id"));

        PayReceipt payReceipt = PayReceipt.builder()
            .status(PENDING)
            .value(analysisDirection.getAnalysis().getCost())
            .doctorsAppointment(analysisDirection.getDoctorsAppointment())
            .paymentType(ANALYSIS)
            .paymentMethod(CARD)
            .build();
        payReceiptRepository.save(payReceipt);
        log.info("New analysis pay receipt was created");

        return payReceipt;
    }

    @Override
    public List<PayReceiptDto> findAllByPatientId(Long patientId) {
        if(patientRepository.existsById(patientId)) {
            log.info("All pay receipts of patient with id = {} was returned", patientId);

            return doctorAppointmentRepository
                .findAllByPatientIdAndStatus(patientId, Status.COMPLETE)
                .stream()
                .map(DoctorAppointment::getPayReceipts)
                .flatMap(List::stream)
                .map()
                .toList();
        }
        else {
            throw new EntityNotFoundException("No patients with such id was found");
        }

    }
}
