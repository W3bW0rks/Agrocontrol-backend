package com.agrocontrol.backend.subscription.domain.model.commands;

import java.time.LocalDate;

public record RenewPaymentCommand(
        LocalDate renewalDate,
        Long paymentId
) {
}
