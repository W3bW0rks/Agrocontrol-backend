package com.agrocontrol.backend.store.domain.model.commands;

public record UpdateProductCommand(
        String name,
        String quantityPerUnit,
        double unitPrice,
        Integer quantity,
        String photoUrl,
        Long productId
) {
}
