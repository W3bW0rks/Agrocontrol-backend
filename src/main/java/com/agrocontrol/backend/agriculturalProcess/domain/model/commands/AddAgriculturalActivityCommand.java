package com.agrocontrol.backend.agriculturalProcess.domain.model.commands;

import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.ActivityType;

import java.time.LocalDate;

public record AddAgriculturalActivityCommand(
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
