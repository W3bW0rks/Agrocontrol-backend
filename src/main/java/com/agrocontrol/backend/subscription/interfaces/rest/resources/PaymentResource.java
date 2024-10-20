package com.agrocontrol.backend.subscription.interfaces.rest.resources;

import java.time.LocalDate;

public record PaymentResource(
        Long id,
        String planType,
        Long userId,
        Long subscriptionId,
        LocalDate startDate,
        LocalDate renewalDate,
        String status,
        double cost
) {
}
