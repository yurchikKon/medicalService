package com.blueTeam.medicalService.service;

import com.blueTeam.medicalService.dto.payment.PayReceiptDto;

import java.util.List;

public interface PayReceiptService {

    PayReceiptDto createAnalysisPayReceipt(Long analysisDirectionId);

    List<PayReceiptDto> findAllByPatientId(Long id);
}
