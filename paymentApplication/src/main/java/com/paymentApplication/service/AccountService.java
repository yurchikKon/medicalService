package com.paymentApplication.service;

import com.paymentApplication.dto.AccountDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface AccountService {

    AccountDto findByClientId(Long id);

    void updateBalance(BigDecimal amount, Long id);
}
