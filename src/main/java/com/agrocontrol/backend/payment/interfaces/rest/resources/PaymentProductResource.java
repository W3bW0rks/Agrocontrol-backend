package com.agrocontrol.backend.payment.interfaces.rest.resources;

import java.time.LocalDate;

public record PaymentProductResource(
        LocalDate date,
        String cardHolderName,
        String cardNumber,
        String ExpireDate,
        String cvv,
        Long productId,
        Integer quantityProduct,
        Long userId,
        Long ownerProductId
) {
}
