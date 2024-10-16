package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources;

import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.ActivityType;

public record AddSeedingToProcessResource(
        String plantType,
        Integer quantityPlanted,
        Long agriculturalProcessId
) {
}
