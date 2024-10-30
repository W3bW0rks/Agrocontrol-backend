package com.agrocontrol.backend.finances.domain.services;

import com.agrocontrol.backend.finances.domain.model.aggregates.Finance;
import com.agrocontrol.backend.finances.domain.model.commands.CreateFinanceCommand;

import java.util.Optional;

public interface FinanceCommandService {
    Optional<Finance> handle(CreateFinanceCommand command);
}
