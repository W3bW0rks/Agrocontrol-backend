package com.agrocontrol.backend.finances.domain.model.commands;

public record CreateFinanceCommand(
        Long agriculturalProcessId,
        String type,
        String description,
        double value
) {
}
