package com.agrocontrol.backend.store.domain.model.commands;

public record IncreaseQuantityCommand(Integer quantity, Long productId) {
}
