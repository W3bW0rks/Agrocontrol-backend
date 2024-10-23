package com.agrocontrol.backend.fields.domain.model.valueobjects;

public record ProducerId(Long producerId) {
    public ProducerId{
        if(producerId <= 0){
            throw new IllegalArgumentException("Invalid producer id");
        }
    }
    public ProducerId(){ this(0L);}
}
