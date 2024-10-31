package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources;

public record AddCropTreatmentToProcessResource(
        String date,
        String treatmentType,
        Long agriculturalProcessId
) {
}
