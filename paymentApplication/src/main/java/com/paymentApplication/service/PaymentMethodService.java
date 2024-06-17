package com.paymentApplication.service;

import com.paymentApplication.dto.PaymentMethodDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentMethodService extends BaseService<PaymentMethodDto, Long> {

    List<PaymentMethodDto> getPaymentMethodsByType(String type);
}
