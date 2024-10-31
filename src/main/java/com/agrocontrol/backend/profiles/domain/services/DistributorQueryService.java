package com.agrocontrol.backend.profiles.domain.services;



import com.agrocontrol.backend.profiles.domain.model.aggregates.Distributor;
import com.agrocontrol.backend.profiles.domain.model.queries.GetDistributorByUserIdAsyncQuery;

import java.util.Optional;

public interface DistributorQueryService {
    Optional<Distributor> handle(GetDistributorByUserIdAsyncQuery query);
}
