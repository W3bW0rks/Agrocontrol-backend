package com.agrocontrol.backend.profiles.application.internal.queryservices;

import com.example.demo.backend.profiles.application.internal.outboundservices.acl.ExternalUserService;
import com.example.demo.backend.profiles.domain.model.aggregates.Distributor;
import com.example.demo.backend.profiles.domain.model.queries.GetDistributorByUserIdAsyncQuery;
import com.example.demo.backend.profiles.domain.services.DistributorQueryService;
import com.example.demo.backend.profiles.infrastructure.persistence.jpa.repositories.DistributorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DistributorQueryServiceImpl implements DistributorQueryService {
    private final DistributorRepository distributorRepository;
    private final ExternalUserService externalUserService;

    public DistributorQueryServiceImpl(DistributorRepository distributorRepository, ExternalUserService externalUserService) {
        this.distributorRepository = distributorRepository;
        this.externalUserService = externalUserService;
    }

    @Override
    public Optional<Distributor> handle(GetDistributorByUserIdAsyncQuery query) {
        // Validar si el usuario existe
        externalUserService.validateUserExists(query.userId());

        // Si existe, continuar con la búsqueda del distribuidor
        return distributorRepository.findDistributorByUserId(query.userId());
    }
}
