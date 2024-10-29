package com.agrocontrol.backend.store.domain.model.commands;

public record ChangeQuantityOfProductCommand(
        String action,
        Integer quantity,
        Long productId
) {
}
