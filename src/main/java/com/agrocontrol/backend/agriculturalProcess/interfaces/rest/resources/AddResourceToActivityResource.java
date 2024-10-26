package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources;

public record AddResourceToActivityResource(
        Long resourceId,
        Integer cost,
        Integer quantity,
        Long activityId,
        Long agriculturalProcessId
) {
}
