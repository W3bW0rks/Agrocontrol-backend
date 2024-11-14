package com.agrocontrol.backend.payment.interfaces.rest.resources;

public record PaymentProductHistoryResource(
        Long productId,
        Integer quantityProduct,
        Long userId,
        Long ownerProductId
) {
}
