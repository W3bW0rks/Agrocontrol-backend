package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources;

import java.time.LocalDate;

public record AgriculturalActivityResource(
        Long id,
        Long agriculturalProcessId,
        String activityType,
        LocalDate date,
        Integer hoursIrrigated,
        String plantType,
        Integer quantityPlanted
) {
}
