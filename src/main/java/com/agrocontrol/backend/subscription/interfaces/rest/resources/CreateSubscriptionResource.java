package com.agrocontrol.backend.subscription.interfaces.rest.resources;

import com.agrocontrol.backend.subscription.domain.model.valueobjects.PlanTypes;

import java.time.LocalDate;

public record CreateSubscriptionResource(
        PlanTypes planType,
        Long userId,
        String status,
        double cost
) {
}
