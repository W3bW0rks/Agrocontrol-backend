package com.agrocontrol.backend.payment.domain.model.commands;

import com.agrocontrol.backend.payment.domain.model.valueobjects.SubscriptionId;

import java.time.LocalDate;

public record CreatePaymentCommand(
        SubscriptionId subscriptionId,
        LocalDate date,
        String state,
        String cardHolderName,
        String cardNumber,
        String ExpireDate,
        String cvv
) {
}
