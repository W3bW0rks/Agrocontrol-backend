package com.agrocontrol.backend.subscription.domain.model.commands;

import com.agrocontrol.backend.subscription.domain.model.valueobjects.PlanTypes;
import com.agrocontrol.backend.subscription.domain.model.valueobjects.UserId;

import java.time.LocalDate;

public record CreatePaymentCommand(
        PlanTypes planType,
        UserId userId,
        LocalDate startDate,
        LocalDate renewalDate,
        String status,
        double cost
) {
}
