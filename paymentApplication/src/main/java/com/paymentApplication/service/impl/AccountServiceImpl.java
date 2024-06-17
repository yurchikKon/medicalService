package com.paymentApplication.service.impl;

import com.paymentApplication.dto.AccountDto;
import com.paymentApplication.model.entity.Currency;
import com.paymentApplication.repository.AccountRepository;
import com.paymentApplication.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountDto findById(Long aLong) {
        return null;
    }

    @Override
    public AccountDto create(AccountDto accountDto) {
        return null;
    }

    @Override
    public AccountDto update(AccountDto accountDto) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public AccountDto getAccountByUserEmail(String email) {
        return null;
    }

    @Override
    public AccountDto findAccountByCreatedDate(LocalDateTime createdDate) {
        return null;
    }

    @Override
    public Page<AccountDto> getAccountsCratedAfter(LocalDateTime createdAt, Pageable pageable) {
        return null;
    }

    @Override
    public Page<AccountDto> getAccountsByBalance(BigDecimal balance, Sort sort, Pageable pageable) {
        return null;
    }

    @Override
    public Page<AccountDto> getAccountsByCurrency(Currency currency, Pageable pageable) {
        return null;
    }
}
