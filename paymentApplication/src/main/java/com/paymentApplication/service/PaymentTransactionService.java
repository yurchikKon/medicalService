package com.paymentApplication.service;

import com.paymentApplication.dto.TransactionDto;
import com.paymentApplication.dto.TransferDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public interface PaymentTransactionService extends BaseService<TransactionDto, Long> {

    @Transactional
    TransactionDto transfer(TransferDto transferDto);
}
