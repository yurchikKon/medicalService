package com.paymentApplication.controller;

import com.paymentApplication.dto.TransferDto;
import com.paymentApplication.service.PaymentTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentTransactionService paymentTransactionService;

    @PostMapping
    public void pay(@RequestBody TransferDto transferDto) {
        paymentTransactionService.transfer(transferDto);
    }
}
