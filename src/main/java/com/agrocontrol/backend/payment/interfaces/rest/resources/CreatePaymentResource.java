package com.agrocontrol.backend.payment.interfaces.rest.resources;

import java.time.LocalDate;

public record CreatePaymentResource(
        Long subscriptionId,
         LocalDate date,
         String state,
         String cardHolderName,
         String cardNumber,
         String ExpireDate,
         String cvv
) {


}
