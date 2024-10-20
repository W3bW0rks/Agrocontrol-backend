package com.agrocontrol.backend.subscription.domain.model.commands;

import com.agrocontrol.backend.subscription.domain.model.valueobjects.PlanTypes;

public record UpdatePlanTypeCommand(
        PlanTypes planType,
        Long paymentId
) {
}
