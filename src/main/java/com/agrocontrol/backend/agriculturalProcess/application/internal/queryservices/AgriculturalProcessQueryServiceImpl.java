package com.agrocontrol.backend.agriculturalProcess.application.internal.queryservices;

import com.agrocontrol.backend.agriculturalProcess.domain.model.aggregates.AgriculturalProcess;
import com.agrocontrol.backend.agriculturalProcess.domain.model.queries.GetAgriculturalProcessByFieldIdQuery;
import com.agrocontrol.backend.agriculturalProcess.domain.model.queries.GetAgriculturalProcessByIdQuery;
import com.agrocontrol.backend.agriculturalProcess.domain.services.AgriculturalProcessQueryService;
import com.agrocontrol.backend.agriculturalProcess.infrastructure.persistence.jpa.repositories.AgriculturalProcessRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgriculturalProcessQueryServiceImpl implements AgriculturalProcessQueryService {
    private final AgriculturalProcessRepository agriculturalProcessRepository;

    public AgriculturalProcessQueryServiceImpl(AgriculturalProcessRepository agriculturalProcessRepository) {
        this.agriculturalProcessRepository = agriculturalProcessRepository;
    }

    @Override
    public Optional<AgriculturalProcess> handle(GetAgriculturalProcessByIdQuery query) {
        return this.agriculturalProcessRepository.findById(query.agriculturalProcessId());
    }

    @Override
    public Optional<AgriculturalProcess> handle(GetAgriculturalProcessByFieldIdQuery query) {
        return this.agriculturalProcessRepository.findByFieldId(query.fieldId());
    }
}
