package com.agrocontrol.backend.agriculturalProcess.domain.model.commands;

public record AddCropTreatmentToProcessCommand(
        String date,
        String treatmentType,
        Long agriculturalProcessId
) {
}
