package com.agrocontrol.backend.payment.interfaces.rest.resources;

import com.agrocontrol.backend.payment.domain.model.valueobjects.SubscriptionId;

import java.time.LocalDate;

public record CreatePaymentResource(
        SubscriptionId subscriptionId,
         LocalDate date,
         String state,
         String cardHolderName,
         String cardNumber,
         String ExpireDate,
         String cvv
) {


}
