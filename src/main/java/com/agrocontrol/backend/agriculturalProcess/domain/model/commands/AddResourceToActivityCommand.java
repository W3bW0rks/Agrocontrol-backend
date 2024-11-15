package com.agrocontrol.backend.agriculturalProcess.domain.model.commands;

public record AddResourceToActivityCommand(
        Long resourceId,
        String description,
        Integer cost,
        Integer quantity,
        Long activityId,
        Long agriculturalProcessId
) {
}
