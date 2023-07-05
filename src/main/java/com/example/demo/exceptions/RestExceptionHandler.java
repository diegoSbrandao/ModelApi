package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException
            (MethodArgumentNotValidException e) {

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        String fieldMessages = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));

        ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();
        validationErrorDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        validationErrorDetails.setTitle("Operação Inválida");
        validationErrorDetails.setMessage("Requisição Inválida");
        validationErrorDetails.setField(fields);
        validationErrorDetails.setFieldMessage(fieldMessages);

        return new ResponseEntity<>(validationErrorDetails, HttpStatus.BAD_REQUEST);

    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundException
            (EntityNotFoundException e) {

        ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();
        validationErrorDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        validationErrorDetails.setTitle("Operação Inválida");
        validationErrorDetails.setMessage("Requisição Inválida");
        validationErrorDetails.setField("id");
        validationErrorDetails.setFieldMessage("Id não existe");

        return new ResponseEntity<>(validationErrorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> RuntimeException
            (RuntimeException e) {

        ValidationErrorDetails validationErrorDetails = new ValidationErrorDetails();
        validationErrorDetails.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        validationErrorDetails.setTitle("Operação Inválida");
        validationErrorDetails.setMessage("Requisição Inválida");
        validationErrorDetails.setField("zipCode");
        validationErrorDetails.setFieldMessage("CEP Inválido");

        return new ResponseEntity<>(validationErrorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
