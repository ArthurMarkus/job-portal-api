package com.arthurmarkus.jobportal.exception;

import com.arthurmarkus.jobportal.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception e, WebRequest webRequest){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<>(errorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleExceptio(MethodArgumentNotValidException e){
        Map<String, String> errors = new HashMap<>();
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        fieldErrors.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(errors);
    }
}
