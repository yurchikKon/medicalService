package com.blueTeam.medicalService.services.interfaces;

import com.blueTeam.medicalService.dto.payment.PayReceiptDto;
import com.blueTeam.medicalService.entities.PayReceipt;

import java.util.List;

public interface PayReceiptService {

    PayReceipt createAnalysisPayReceipt(Long analysisDirectionId);

    List<PayReceiptDto> findAllByPatientId(Long id);
}
