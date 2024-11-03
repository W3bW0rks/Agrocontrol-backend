package com.agrocontrol.backend.finances.application.internal.queryservices;

import com.agrocontrol.backend.finances.domain.model.aggregates.Finance;
import com.agrocontrol.backend.finances.domain.model.queries.GetFinancesByAgriculturalProcessIdQuery;
import com.agrocontrol.backend.finances.domain.services.FinanceQueryService;
import com.agrocontrol.backend.finances.infrastructure.persistence.jpa.repositories.FinanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinanceQueryServiceImpl implements FinanceQueryService {

    private final FinanceRepository financeRepository;

    public FinanceQueryServiceImpl(FinanceRepository financeRepository) {
        this.financeRepository = financeRepository;
    }

    @Override
    public List<Finance> handle(GetFinancesByAgriculturalProcessIdQuery query) {
        return this.financeRepository.findAllByAgriculturalProcessId(query.agriculturalProcessId());
    }
}
