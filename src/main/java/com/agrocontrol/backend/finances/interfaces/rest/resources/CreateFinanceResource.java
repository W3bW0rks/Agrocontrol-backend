package com.agrocontrol.backend.finances.interfaces.rest.resources;

public record CreateFinanceResource(
        Long agriculturalProcessId,
        String type,
        double value
) {
}
