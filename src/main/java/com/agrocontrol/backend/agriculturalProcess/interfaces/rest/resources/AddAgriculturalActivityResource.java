package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources;

import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.ActivityType;

public record AddAgriculturalActivityResource(
        Long agriculturalProcessId,
        ActivityType activityType,
        Integer hoursIrrigated,
        String plantType,
        Integer quantityPlanted,
        String cropTreatmentType,
        double totalQuantityInKg,
        double pricePerKg,
        double totalIncome
) {
}
