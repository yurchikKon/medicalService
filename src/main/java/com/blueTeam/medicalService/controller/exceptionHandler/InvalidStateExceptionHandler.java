package com.blueTeam.medicalService.controller.exceptionHandler;

import com.blueTeam.medicalService.exception.InvalidStateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class InvalidStateExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidStateException.class})
    protected ResponseEntity<ProblemDetail> handle(InvalidStateException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNPROCESSABLE_ENTITY, exception.getMessage());

        return ResponseEntity.badRequest()
            .body(problemDetail);
    }
}
