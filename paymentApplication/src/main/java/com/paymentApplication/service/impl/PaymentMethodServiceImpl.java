package com.paymentApplication.service.impl;

import com.paymentApplication.dto.PaymentMethodDto;
import com.paymentApplication.repository.PaymentMethodRepository;
import com.paymentApplication.service.PaymentMethodService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private PaymentMethodRepository paymentMethodRepository;

    @Override
    public PaymentMethodDto findById(Long aLong) {
        return null;
    }

    @Override
    public PaymentMethodDto create(PaymentMethodDto paymentMethodDto) {
        return null;
    }

    @Override
    public PaymentMethodDto update(PaymentMethodDto paymentMethodDto) {
        return null;
    }

    @Override
    public void delete(Long aLong) {

    }

    @Override
    public List<PaymentMethodDto> getPaymentMethodsByType(String type) {
        return List.of();
    }
}
