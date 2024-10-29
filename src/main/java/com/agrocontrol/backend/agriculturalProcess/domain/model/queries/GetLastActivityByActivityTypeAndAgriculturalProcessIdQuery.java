package com.agrocontrol.backend.agriculturalProcess.domain.model.queries;

import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.ActivityType;

public record GetLastActivityByActivityTypeAndAgriculturalProcessIdQuery(
        Long agriculturalProcessId,
        ActivityType activityType
) {
}
