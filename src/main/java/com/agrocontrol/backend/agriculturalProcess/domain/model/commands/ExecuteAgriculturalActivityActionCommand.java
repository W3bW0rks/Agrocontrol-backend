package com.agrocontrol.backend.agriculturalProcess.domain.model.commands;

public record ExecuteAgriculturalActivityActionCommand(
        Long agriculturalProcessId,
        Long activityId,
        String action
) {
}
