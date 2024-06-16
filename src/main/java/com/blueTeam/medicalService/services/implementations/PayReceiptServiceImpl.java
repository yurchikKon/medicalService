package com.blueTeam.medicalService.services.implementations;

import com.blueTeam.medicalService.dto.payment.PayReceiptDto;
import com.blueTeam.medicalService.entities.AnalysisDirection;
import com.blueTeam.medicalService.entities.DoctorAppointment;
import com.blueTeam.medicalService.entities.PayReceipt;
import com.blueTeam.medicalService.entities.enums.Status;
import com.blueTeam.medicalService.entities.enums.Usage;
import com.blueTeam.medicalService.exceptions.InvalidStateException;
import com.blueTeam.medicalService.mapper.PayReceiptMapper;
import com.blueTeam.medicalService.repositories.*;
import com.blueTeam.medicalService.services.interfaces.PayReceiptService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.blueTeam.medicalService.entities.enums.PaymentMethod.CARD;
import static com.blueTeam.medicalService.entities.enums.PaymentType.ANALYSIS;
import static com.blueTeam.medicalService.entities.enums.ReceiptStatus.COMPLETED;
import static com.blueTeam.medicalService.entities.enums.ReceiptStatus.PENDING;

@Service
@RequiredArgsConstructor
@Slf4j
public class PayReceiptServiceImpl implements PayReceiptService {
    private final AnalysisDirectionRepository analysisDirectionRepository;
    private final PayReceiptRepository payReceiptRepository;
    private final DoctorAppointmentRepository doctorAppointmentRepository;
    private final PatientRepository patientRepository;
    private final PayReceiptMapper payReceiptMapper;

    @Override
    @Transactional
    public PayReceiptDto createAnalysisPayReceipt(Long analysisDirectionId) {
        AnalysisDirection analysisDirection = analysisDirectionRepository.findById(analysisDirectionId)
            .orElseThrow(() -> new EntityNotFoundException("no analysis direction with such id"));

        if (analysisDirection.getUsage().equals(Usage.USED)) {
            PayReceipt payReceipt = PayReceipt.builder()
                .status(PENDING)
                .value(analysisDirection.getAnalysis().getCost())
                .doctorsAppointment(analysisDirection.getDoctorsAppointment())
                .paymentType(ANALYSIS)
                .paymentMethod(CARD)
                .build();
            payReceiptRepository.save(payReceipt);
            log.info("New analysis pay receipt was created");

            return payReceiptMapper.mapToDto(payReceipt);
        }
        else {
            throw new InvalidStateException("Analysis has not been passed yet");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<PayReceiptDto> findAllByPatientId(Long patientId) {
        if(patientRepository.existsById(patientId)) {
            log.info("All pay receipts of patient with id = {} was returned", patientId);

            return doctorAppointmentRepository
                .findAllByPatientIdAndStatus(patientId, Status.COMPLETED)
                .stream()
                .map(DoctorAppointment::getPayReceipts)
                .flatMap(List::stream)
                .filter(receipt -> !receipt.getStatus().equals(COMPLETED))
                .map(payReceiptMapper::mapToDto)
                .toList();
        }
        else {
            throw new EntityNotFoundException("No patients with such id was found");
        }

    }
}
