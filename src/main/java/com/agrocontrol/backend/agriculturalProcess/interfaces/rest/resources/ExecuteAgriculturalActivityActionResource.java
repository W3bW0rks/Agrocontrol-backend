package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources;

public record ExecuteAgriculturalActivityActionResource(
        Long agriculturalProcessId,
        Long activityId,
        String action
) {
}
