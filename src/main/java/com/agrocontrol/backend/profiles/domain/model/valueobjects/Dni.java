package com.agrocontrol.backend.profiles.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Dni(String dni) {
    public Dni{
        if (dni == null || !dni.matches("\\d{8}")) {
            throw new IllegalArgumentException("Invalid DNI: must be a positive number with exactly 8 digits");
        }
    }

    public Dni(){
        this("00000000"); // Valor por defecto opcional
    }
}
