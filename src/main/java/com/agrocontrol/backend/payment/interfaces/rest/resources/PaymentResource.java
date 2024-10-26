package com.agrocontrol.backend.payment.interfaces.rest.resources;

import com.agrocontrol.backend.payment.domain.model.valueobjects.SubscriptionId;

public record PaymentResource(
        Long id,
        Long subscriptionId,
        String date,
        String state,
        String cardHolderName,
        String cardNumber,
        String ExpireDate,
        String cvv

) {

}
