package com.agrocontrol.backend.finances.interfaces.rest.resources;

import java.time.LocalDate;

public record FinanceResource(
        Long id,
        Long userId,
        LocalDate date,
        String type,
        double value
) {
}
