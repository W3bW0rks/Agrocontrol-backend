package com.agrocontrol.backend.subscription.interfaces.rest.resources;

import com.agrocontrol.backend.subscription.domain.model.valueobjects.PlanTypes;

import java.time.LocalDate;

public record CreatePaymentResource(
        PlanTypes planType,
        Long userId,
        Long subscriptionId,
        LocalDate startDate,
        LocalDate renewalDate,
        String status,
        double cost
) {
}
