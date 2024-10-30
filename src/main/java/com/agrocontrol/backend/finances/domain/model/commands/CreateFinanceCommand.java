package com.agrocontrol.backend.finances.domain.model.commands;

public record CreateFinanceCommand(
        Long userId,
        String type,
        double value
) {
}
