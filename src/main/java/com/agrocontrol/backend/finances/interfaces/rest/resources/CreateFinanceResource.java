package com.agrocontrol.backend.finances.interfaces.rest.resources;

public record CreateFinanceResource(
        Long userId,
        String type,
        double value
) {
}
