package com.blueTeam.medicalService.controllers.exceptionHandlers;

import com.blueTeam.medicalService.exceptions.ResourceAlreadyExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ResourceAlreadyExistExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {ResourceAlreadyExistException.class})
    protected ResponseEntity<ProblemDetail> handle(ResourceAlreadyExistException exception) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, exception.getMessage());

        return ResponseEntity.badRequest()
            .body(problemDetail);
    }
}
