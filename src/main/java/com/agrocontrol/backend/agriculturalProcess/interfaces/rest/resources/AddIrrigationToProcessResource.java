package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources;

public record AddIrrigationToProcessResource(
        Integer hoursIrrigated,
        Long agriculturalProcessId
) {
}
