package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.List;

public record AgriculturalProcessResource(
        Long id,
        Long fieldId,
        LocalDate startDate,
        LocalDate endDate,
        boolean isFinished,
        List<AgriculturalActivityResource> activities
) {
}
