package com.agrocontrol.backend.agriculturalProcess.domain.services;

import com.agrocontrol.backend.agriculturalProcess.domain.model.queries.GetAgriculturalProcessByIdQuery;

import java.util.Optional;

public interface AgriculturalProcessQueryService {
    Optional<AgriculturalProcessCommandService> handle(GetAgriculturalProcessByIdQuery query);
}
