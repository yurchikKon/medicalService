package com.paymentApplication.service.impl;

import com.paymentApplication.dto.AccountDto;
import com.paymentApplication.mapper.AccountMapper;
import com.paymentApplication.repository.AccountRepository;
import com.paymentApplication.service.AccountService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountDto findByClientId(Long id) {
        return accountRepository.findAccount(id)
                .map(accountMapper::mapToDto)
                .orElseThrow(() -> new EntityNotFoundException("Account not found with client id: " + id));
    }

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void updateBalance(BigDecimal amount, Long id) {
        accountRepository.updateBalance(amount, id);
    }
}
