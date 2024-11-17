package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources;

public record AddSeedingToProcessResource(
        String date,
        String plantType,
        Integer quantityPlanted,
        Long agriculturalProcessId
) {
}
