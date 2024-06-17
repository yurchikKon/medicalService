package com.paymentApplication.service.impl;

import com.paymentApplication.dto.TransactionDto;
import com.paymentApplication.dto.TransferDto;
import com.paymentApplication.repository.PaymentTransactionRepository;
import com.paymentApplication.service.PaymentTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentTransactionalServiceImpl implements PaymentTransactionService {

    private final PaymentTransactionRepository transactionRepository;

    @Override
    public TransactionDto findById(Long aLong) {
        return null;
    }

    @Override
    public TransactionDto create(TransactionDto transactionDto) {
        return null;
    }

    @Override
    public TransactionDto update(TransactionDto transactionDto) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public TransactionDto transfer(TransferDto transferDto) {
        return null;
    }
}
