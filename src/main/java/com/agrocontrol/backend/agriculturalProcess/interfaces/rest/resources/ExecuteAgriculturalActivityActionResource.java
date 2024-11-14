package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources;

public record ExecuteAgriculturalActivityActionResource(
        Long agriculturalProcessId,
        String action
) {
}
