package com.agrocontrol.backend.finances.application.internal.commandservices;

import com.agrocontrol.backend.finances.domain.model.aggregates.Finance;
import com.agrocontrol.backend.finances.domain.model.commands.CreateFinanceCommand;
import com.agrocontrol.backend.finances.domain.services.FinanceCommandService;
import com.agrocontrol.backend.finances.infrastructure.persistence.jpa.repositories.FinanceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FinanceCommandServiceImpl implements FinanceCommandService {
    private final FinanceRepository financeRepository;

    public FinanceCommandServiceImpl(FinanceRepository financeRepository) {
        this.financeRepository = financeRepository;
    }

    @Override
    public Optional<Finance> handle(CreateFinanceCommand command) {
        var finance = new Finance(command);

        var savedFinance = this.financeRepository.save(finance);

        return Optional.of(savedFinance);
    }
}
