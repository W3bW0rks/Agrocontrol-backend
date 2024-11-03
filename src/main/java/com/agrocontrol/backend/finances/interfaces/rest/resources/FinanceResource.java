package com.agrocontrol.backend.finances.interfaces.rest.resources;

import java.time.LocalDate;

public record FinanceResource(
        Long id,
        Long agriculturalProcessId,
        LocalDate date,
        String type,
        double value
) {
}
