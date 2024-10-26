package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources;

public record AddHarvestToProcessResource(
        String date,
        double quantityInKg,
        double pricePerKg,
        Long agriculturalProcessId
) {
}