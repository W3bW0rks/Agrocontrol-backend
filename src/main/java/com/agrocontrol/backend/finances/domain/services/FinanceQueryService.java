package com.agrocontrol.backend.finances.domain.services;

import com.agrocontrol.backend.finances.domain.model.aggregates.Finance;
import com.agrocontrol.backend.finances.domain.model.queries.GetFinancesByUserIdQuery;

import java.util.List;

public interface FinanceQueryService {
    List<Finance> handle(GetFinancesByUserIdQuery query);
}
