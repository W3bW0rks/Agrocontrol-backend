package com.agrocontrol.backend.store.interfaces.rest.resources;

public record CreatePaymentProductResource(
        Long productId,
        Integer quantityProduct,
        Long userId
) {

}
