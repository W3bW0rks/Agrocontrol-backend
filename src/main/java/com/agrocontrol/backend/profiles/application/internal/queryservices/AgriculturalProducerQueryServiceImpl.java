package com.agrocontrol.backend.profiles.application.internal.queryservices;

import com.agrocontrol.backend.profiles.domain.model.aggregates.AgriculturalProducer;
import com.agrocontrol.backend.profiles.domain.model.queries.GetAgriculturalProducerByUserIdAsyncQuery;
import com.agrocontrol.backend.profiles.domain.services.AgriculturalProducerQueryService;
import com.agrocontrol.backend.profiles.infrastructure.persistence.jpa.repositories.AgriculturalProducerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgriculturalProducerQueryServiceImpl implements AgriculturalProducerQueryService {
    private final AgriculturalProducerRepository agriculturalProducerRepository;

    public AgriculturalProducerQueryServiceImpl(AgriculturalProducerRepository agriculturalProducerRepository) {
        this.agriculturalProducerRepository = agriculturalProducerRepository;
    }

    @Override
    public Optional<AgriculturalProducer> handle(GetAgriculturalProducerByUserIdAsyncQuery query) {
        return agriculturalProducerRepository.findAgriculturalProducerByUserId(query.userId());
    }
}
