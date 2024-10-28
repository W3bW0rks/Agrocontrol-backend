package com.agrocontrol.backend.subscription.interfaces.rest.resources;

import java.time.LocalDate;

public record SubscriptionResource(
        Long id,
        String planType,
        Long userId,
        LocalDate startDate,
        LocalDate renewalDate,
        String status,
        double cost
) {
}
