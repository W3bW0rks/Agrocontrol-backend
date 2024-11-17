package com.agrocontrol.backend.subscription.domain.model.commands;

import com.agrocontrol.backend.subscription.domain.model.valueobjects.PlanTypes;

public record CreateSubscriptionCommand(
        PlanTypes planType,
        Long userId,
        String status,
        double cost
) {
}
