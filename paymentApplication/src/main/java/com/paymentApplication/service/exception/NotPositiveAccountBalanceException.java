package com.paymentApplication.service.exception;

public class NotPositiveAccountBalanceException extends RuntimeException {
    public NotPositiveAccountBalanceException() {
    }

    public NotPositiveAccountBalanceException(String message) {
        super(message);
    }
}
