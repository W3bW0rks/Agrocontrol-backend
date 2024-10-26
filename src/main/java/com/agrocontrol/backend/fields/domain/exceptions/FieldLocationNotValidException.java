package com.agrocontrol.backend.fields.domain.exceptions;

public class FieldLocationNotValidException extends RuntimeException {
    public FieldLocationNotValidException(String location) {
        super("Field location %s cannot be null or empty".formatted(location));
    }
}
