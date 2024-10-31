package com.agrocontrol.backend.profiles.application.internal.queryservices;

import com.agrocontrol.backend.profiles.application.internal.outboundservices.acl.ExternalUserService;
import com.agrocontrol.backend.profiles.domain.model.aggregates.AgriculturalProducer;
import com.agrocontrol.backend.profiles.domain.model.queries.GetAgriculturalProducerByUserIdAsyncQuery;
import com.agrocontrol.backend.profiles.domain.services.AgriculturalProducerQueryService;
import com.agrocontrol.backend.profiles.infrastructure.persistence.jpa.repositories.AgriculturalProducerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgriculturalProducerQueryServiceImpl implements AgriculturalProducerQueryService {
    private final ExternalUserService externalUserService;
    private final AgriculturalProducerRepository agriculturalProducerRepository;

    public AgriculturalProducerQueryServiceImpl(ExternalUserService externalUserService, AgriculturalProducerRepository agriculturalProducerRepository) {
        this.externalUserService = externalUserService;
        this.agriculturalProducerRepository = agriculturalProducerRepository;
    }

    @Override
    public Optional<AgriculturalProducer> handle(GetAgriculturalProducerByUserIdAsyncQuery query) {
        // Validar si el usuario existe
        externalUserService.validateUserExists(query.userId());

        // Si existe, continuar con la búsqueda del productor agrícola
        return agriculturalProducerRepository.findAgriculturalProducerByUserId(query.userId());
    }
}
