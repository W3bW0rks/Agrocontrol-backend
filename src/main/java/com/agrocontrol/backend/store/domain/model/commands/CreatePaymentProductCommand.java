package com.agrocontrol.backend.store.domain.model.commands;

public record CreatePaymentProductCommand(
        Long productId,
        Integer quantityProduct,
        Long userId
) {
}
