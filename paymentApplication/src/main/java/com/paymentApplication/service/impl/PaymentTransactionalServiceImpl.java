package com.paymentApplication.service.impl;

import com.paymentApplication.dto.AccountDto;
import com.paymentApplication.dto.TransferDto;
import com.paymentApplication.mapper.AccountMapper;
import com.paymentApplication.mapper.PaymentMethodMapper;
import com.paymentApplication.model.entity.PaymentTransaction;
import com.paymentApplication.repository.PaymentTransactionRepository;
import com.paymentApplication.service.AccountService;
import com.paymentApplication.service.PaymentTransactionService;
import com.paymentApplication.service.exception.NotPositiveAccountBalanceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static java.math.BigDecimal.valueOf;
import static java.util.Optional.ofNullable;

@Service
@RequiredArgsConstructor
public class PaymentTransactionalServiceImpl implements PaymentTransactionService {

    private final PaymentTransactionRepository transactionRepository;
    private final AccountService accountService;

    private final AccountMapper accountMapper;
    private final PaymentMethodMapper paymentMethodMapper;

    @Override
    @Transactional(isolation = Isolation.READ_UNCOMMITTED, rollbackFor = Exception.class)
    public void transfer(TransferDto dto) {
        AccountDto sender = getClientAccount(dto.senderId());
        AccountDto receiver = getClientAccount(dto.receiverId());

        accountService.updateBalance(sender.balance().subtract(dto.amount()), sender.id());
        accountService.updateBalance(receiver.balance().add(dto.amount()), receiver.id());

        saveTransaction(dto, receiver, sender);
    }

    private AccountDto getClientAccount(Long clientId) {
        return ofNullable(accountService.findByClientId(clientId))
                .filter(this::checkAccountBalance)
                .orElseThrow(() -> new NotPositiveAccountBalanceException("Account id: %d balance less than 0".formatted(clientId)));
    }

    private void saveTransaction(TransferDto dto, AccountDto receiver, AccountDto sender) {
        PaymentTransaction transaction = PaymentTransaction.builder()
                .amount(dto.amount())
                .createdAt(LocalDateTime.now())
                .receiver(accountMapper.mapToEntity(receiver))
                .sender(accountMapper.mapToEntity(sender))
                .paymentMethod(paymentMethodMapper.mapToEntity(dto.paymentMethod()))
                .currency(dto.currency())
                .build();

        transactionRepository.save(transaction);
    }

    private boolean checkAccountBalance(AccountDto dto) {
        return dto.balance().compareTo(valueOf(0.00)) > 0;
    }
}
