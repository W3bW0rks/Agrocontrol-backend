package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources;

public record AgriculturalActivityResource(
        Long id,
        Long agriculturalProcessId,
        String activityType,
        String date,
        String activityStatus,
        Integer hoursIrrigated,
        String plantType,
        Integer quantityPlanted,
        String treatmentType
) {
}
