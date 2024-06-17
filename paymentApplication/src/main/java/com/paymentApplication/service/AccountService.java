package com.paymentApplication.service;

import com.paymentApplication.dto.AccountDto;
import com.paymentApplication.model.entity.Currency;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public interface AccountService extends BaseService<AccountDto, Long> {

    AccountDto getAccountByUserEmail(String email);

    AccountDto findAccountByCreatedDate(LocalDateTime createdDate);

    Page<AccountDto> getAccountsCratedAfter(LocalDateTime createdAt, Pageable pageable);

    Page<AccountDto> getAccountsByBalance(BigDecimal balance, Sort sort, Pageable pageable);

    Page<AccountDto> getAccountsByCurrency(Currency currency, Pageable pageable);
}
