package com.agrocontrol.backend.finances.domain.model.queries;

import com.agrocontrol.backend.finances.domain.model.valueobjects.AgriculturalProcessId;

public record GetFinancesByAgriculturalProcessIdQuery(AgriculturalProcessId agriculturalProcessId) {
}