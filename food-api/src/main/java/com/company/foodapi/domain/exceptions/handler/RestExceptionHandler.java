package com.company.foodapi.domain.exceptions.handler;

import com.company.foodapi.domain.exceptions.ResourceNotFound;
import com.company.foodapi.domain.exceptions.ResourceNotFoundDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ResourceNotFoundDetails> handlerBadRequestException(ResourceNotFound bre){
        return new ResponseEntity<>(
                ResourceNotFoundDetails.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.NOT_FOUND.value())
                        .title("Resource Not Found Exception. Check the Documentation")
                        .details(bre.getMessage())
                        .developerMessage(bre.fillInStackTrace().getMessage())
                        .build(), HttpStatus.NOT_FOUND);
    }
}
