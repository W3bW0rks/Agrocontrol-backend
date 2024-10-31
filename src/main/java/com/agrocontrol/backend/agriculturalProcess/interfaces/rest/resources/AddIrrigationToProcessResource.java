package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources;

public record AddIrrigationToProcessResource(
        String date,
        Integer hoursIrrigated,
        Long agriculturalProcessId
) {
}
