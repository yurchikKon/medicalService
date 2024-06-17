package com.paymentApplication.service.impl;

import com.paymentApplication.dto.AccountDto;
import com.paymentApplication.dto.PaymentMethodDto;
import com.paymentApplication.dto.TransactionLogDto;
import com.paymentApplication.model.entity.Status;
import com.paymentApplication.repository.TransactionLogRepository;
import com.paymentApplication.service.TransactionLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionLogServiceImpl implements TransactionLogService {

    private final TransactionLogRepository transactionLogRepository;

    @Override
    public TransactionLogDto findById(Long aLong) {
        return null;
    }

    @Override
    public TransactionLogDto create(TransactionLogDto transactionLogDto) {
        return null;
    }

    @Override
    public TransactionLogDto update(TransactionLogDto transactionLogDto) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<TransactionLogDto> getLogsByStatus(Status status, Pageable pageable) {
        return List.of();
    }

    @Override
    public List<TransactionLogDto> getLogsByPaymentMethod(PaymentMethodDto paymentMethodDto, Pageable pageable) {
        return List.of();
    }

    @Override
    public List<TransactionLogDto> getLogsByAccount(AccountDto accountDto, Pageable pageable) {
        return List.of();
    }

    @Override
    public List<TransactionLogDto> getLogsBeforeDate(LocalDateTime loggedAt, Pageable pageable) {
        return List.of();
    }

    @Override
    public List<TransactionLogDto> getLogsAfterDate(LocalDateTime loggedAt, Pageable pageable) {
        return List.of();
    }
}
