package com.agrocontrol.backend.store.domain.model.commands;

public record ReduceProductQuantityCommand(Long productId, Integer quantityToReduce) {
}
