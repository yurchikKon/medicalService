package com.paymentApplication.service;

import com.paymentApplication.dto.AccountDto;
import com.paymentApplication.dto.PaymentMethodDto;
import com.paymentApplication.dto.TransactionLogDto;
import com.paymentApplication.model.entity.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface TransactionLogService extends BaseService<TransactionLogDto, Long> {

    List<TransactionLogDto> getLogsByStatus(Status status, Pageable pageable);

    List<TransactionLogDto> getLogsByPaymentMethod(PaymentMethodDto paymentMethodDto, Pageable pageable);

    List<TransactionLogDto> getLogsByAccount(AccountDto accountDto, Pageable pageable);

    List<TransactionLogDto> getLogsBeforeDate(LocalDateTime loggedAt, Pageable pageable);

    List<TransactionLogDto> getLogsAfterDate(LocalDateTime loggedAt, Pageable pageable);
}
