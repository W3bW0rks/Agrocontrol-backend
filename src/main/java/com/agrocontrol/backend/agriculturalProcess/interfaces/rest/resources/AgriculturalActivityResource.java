package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources;

public record AgriculturalActivityResource(
        Long id,
        Long agriculturalProcessId,
        String activityType,
        String date,
        Integer hoursIrrigated,
        String plantType,
        Integer quantityPlanted
) {
}
