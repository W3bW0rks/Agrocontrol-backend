package com.agrocontrol.backend.fields.domain.exceptions;

public class FieldSizeNotValidException extends RuntimeException{
    public FieldSizeNotValidException(Integer size){
        super("Field size %s must be greater than zero".formatted(size));
    }
}
