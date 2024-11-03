package com.agrocontrol.backend.finances.domain.model.valueobjects;

public record AgriculturalProcessId(Long agriculturalProcessId) {
    public AgriculturalProcessId {
        if (agriculturalProcessId <= 0) {
            throw new IllegalArgumentException("Agricultural Process Id must be greater than 0");
        }
    }

    public AgriculturalProcessId() { this(0L); }
}