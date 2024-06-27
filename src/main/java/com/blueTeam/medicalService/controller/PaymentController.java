package com.blueTeam.medicalService.controller;

import com.blueTeam.medicalService.client.PaymentServiceClient;
import com.blueTeam.medicalService.dto.payment.client.TransferDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payment")
public class PaymentController {

    private final PaymentServiceClient paymentServiceClient;

    @PostMapping
    public ResponseEntity<?> pay(@RequestBody TransferDto transferDto) {
        return ResponseEntity.ok()
                .body(paymentServiceClient.pay(transferDto));
    }

    @ExceptionHandler(HttpClientErrorException.BadRequest.class)
    public ResponseEntity<ProblemDetail> handleBadRequest(HttpClientErrorException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getResponseBodyAsString());

        problemDetail.setProperty("error", exception.getResponseBodyAsString());

        return ResponseEntity
                .badRequest()
                .body(problemDetail);
    }
}
