package com.agrocontrol.backend.payment.interfaces.rest.resources;

public record CreatePaymentProductResource(
        String cardHolderName,
        String cardNumber,
        String ExpireDate,
        String cvv,
        Long productId,
        Integer quantityProduct,
        Long userId
) {

}
