package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources;

public record AddResourceToActivityResource(
        Long resourceId,
        String description,
        Integer cost,
        Integer quantity,
        Long activityId,
        Long agriculturalProcessId
) {
}
