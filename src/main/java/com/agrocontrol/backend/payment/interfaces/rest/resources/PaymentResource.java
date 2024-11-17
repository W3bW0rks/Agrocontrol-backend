package com.agrocontrol.backend.payment.interfaces.rest.resources;

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
