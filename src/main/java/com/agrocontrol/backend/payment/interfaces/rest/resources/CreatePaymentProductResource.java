package com.agrocontrol.backend.payment.interfaces.rest.resources;

public record CreatePaymentProductResource(
        Long productId,
        Integer quantityProduct,
        Long userId
) {

}
