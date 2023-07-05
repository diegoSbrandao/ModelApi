package com.example.demo.exceptions;

import lombok.*;

@Data
public class ValidationErrorDetails extends ErrorMessage {

    private String field;
    private String fieldMessage;
}
