package com.agrocontrol.backend.store.domain.model.commands;

public record UpdateProductOwnerCommand(Long userId, Long productId) {
}
