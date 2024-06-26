package com.paymentApplication.service;

import com.paymentApplication.dto.TransferDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface PaymentTransactionService {

    @Transactional
    void transfer(TransferDto transferDto);
}
