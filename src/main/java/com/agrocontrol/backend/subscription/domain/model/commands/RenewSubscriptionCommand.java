package com.agrocontrol.backend.subscription.domain.model.commands;

import java.time.LocalDate;

public record RenewSubscriptionCommand(
        LocalDate renewalDate,
        Long paymentId
) {
}
