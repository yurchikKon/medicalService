package com.paymentApplication.controller.handler;

import com.paymentApplication.service.exception.NotPositiveAccountBalanceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "com.paymentApplication.controller")
public class NotPositiveAccountBalanceExceptionHandler {

    @ExceptionHandler(NotPositiveAccountBalanceException.class)
    public ResponseEntity<ProblemDetail> handleNotPositiveAccountBalanceException(NotPositiveAccountBalanceException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());

        problemDetail.setProperty("error", ex.getMessage());

        return ResponseEntity
                .badRequest()
                .body(problemDetail);
    }
}
