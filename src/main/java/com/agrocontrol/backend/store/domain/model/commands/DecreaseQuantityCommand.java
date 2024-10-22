package com.agrocontrol.backend.store.domain.model.commands;

public record DecreaseQuantityCommand(Integer quantity, Long productId) {
}
