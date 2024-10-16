package com.agrocontrol.backend.agriculturalProcess.domain.services;

import com.agrocontrol.backend.agriculturalProcess.domain.model.aggregates.AgriculturalProcess;
import com.agrocontrol.backend.agriculturalProcess.domain.model.queries.*;

import java.util.Optional;

public interface AgriculturalProcessQueryService {
    Optional<AgriculturalProcess> handle(GetAgriculturalProcessByIdQuery query);
    Optional<AgriculturalProcess> handle(GetAgriculturalProcessByFieldIdQuery query);
}
