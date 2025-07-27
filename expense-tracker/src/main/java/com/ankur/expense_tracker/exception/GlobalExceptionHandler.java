package com.ankur.expense_tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleResourceNotFoundException(ResourceNotFoundException ex){
        Map<String,Object> data = new HashMap<>();
        data.put("timeStamp", LocalDate.now());
        data.put("message", ex.getMessage());
        data.put("status", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TitleNotFoundExcepton.class)
    public ResponseEntity<Map<String,Object>> handleTitleNotFoundException(TitleNotFoundExcepton ex){
        Map<String,Object> data = new HashMap<>();
        data.put("timeStamp", LocalDate.now());
        data.put("message", ex.getMessage());
        data.put("status", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(data,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return new ResponseEntity<>("Internal Server Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
