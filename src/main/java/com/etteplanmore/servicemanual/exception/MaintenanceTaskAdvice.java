package com.etteplanmore.servicemanual.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class MaintenanceTaskAdvice {

    @ResponseBody
    @ExceptionHandler(MaintenanceTaskNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    ResponseEntity<ErrorMessage> maintenanceTaskNotFound(MaintenanceTaskNotFoundException ex) {
    	ErrorMessage errorMessage= new ErrorMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}