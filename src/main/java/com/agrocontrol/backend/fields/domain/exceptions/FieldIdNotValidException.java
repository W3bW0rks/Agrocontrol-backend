package com.agrocontrol.backend.fields.domain.exceptions;

public class FieldIdNotValidException extends RuntimeException {
    public FieldIdNotValidException(Long fieldId) {
        super("Field with ID %s is not valid".formatted(fieldId));
    }
}
