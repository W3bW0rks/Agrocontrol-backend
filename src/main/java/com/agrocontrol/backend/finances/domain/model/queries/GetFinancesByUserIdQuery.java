package com.agrocontrol.backend.finances.domain.model.queries;

import com.agrocontrol.backend.finances.domain.model.valueobjects.UserId;

public record GetFinancesByUserIdQuery(UserId userId) {
}