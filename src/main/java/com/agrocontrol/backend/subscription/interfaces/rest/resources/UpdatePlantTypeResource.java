package com.agrocontrol.backend.subscription.interfaces.rest.resources;

import com.agrocontrol.backend.subscription.domain.model.valueobjects.PlanTypes;

public record UpdatePlantTypeResource(
        PlanTypes planType
) {
}
