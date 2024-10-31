package com.agrocontrol.backend.profiles.domain.services;



import com.agrocontrol.backend.profiles.domain.model.aggregates.AgriculturalProducer;
import com.agrocontrol.backend.profiles.domain.model.queries.GetAgriculturalProducerByUserIdAsyncQuery;

import java.util.Optional;

public interface AgriculturalProducerQueryService {
    Optional<AgriculturalProducer> handle(GetAgriculturalProducerByUserIdAsyncQuery query);
}
