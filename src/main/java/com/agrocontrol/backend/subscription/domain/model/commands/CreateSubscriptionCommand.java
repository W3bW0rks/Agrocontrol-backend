package com.agrocontrol.backend.subscription.domain.model.commands;

import com.agrocontrol.backend.subscription.domain.model.valueobjects.PlanTypes;

import java.time.LocalDate;

public record CreateSubscriptionCommand(
        PlanTypes planType,
        Long userId,
        LocalDate startDate,
        LocalDate renewalDate,
        String status,
        double cost
) {
}
