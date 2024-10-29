package com.agrocontrol.backend.profiles.interfaces.rest;

import com.agrocontrol.backend.profiles.domain.model.queries.GetAgriculturalProducerByUserIdAsyncQuery;
import com.agrocontrol.backend.profiles.domain.model.queries.GetDistributorByUserIdAsyncQuery;
import com.agrocontrol.backend.profiles.domain.services.AgriculturalProducerQueryService;
import com.agrocontrol.backend.profiles.domain.services.DistributorQueryService;
import com.agrocontrol.backend.profiles.interfaces.rest.resource.AgriculturalProducerResource;
import com.agrocontrol.backend.profiles.interfaces.rest.resource.DistributorResource;
import com.agrocontrol.backend.profiles.interfaces.rest.transform.AgriculturalProducerResourceFromEntityAssembler;
import com.agrocontrol.backend.profiles.interfaces.rest.transform.DistributorResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Profiles", description = "Profiles Management Endpoints")
public class ProfileController {
    private final AgriculturalProducerQueryService agriculturalProducerQueryService;
    private final DistributorQueryService distributorQueryService;

    public ProfileController(AgriculturalProducerQueryService agriculturalProducerQueryService,
                             DistributorQueryService distributorQueryService) {
        this.agriculturalProducerQueryService = agriculturalProducerQueryService;
        this.distributorQueryService = distributorQueryService;
    }

    @GetMapping(value = "/agricultural-producer/{userId}")
    public ResponseEntity<AgriculturalProducerResource> getAgriculturalProducer(@PathVariable Long userId) {
        var getAgriculturalProducerByUserIdQuery = new GetAgriculturalProducerByUserIdAsyncQuery(userId);
        var agriculturalProducer = agriculturalProducerQueryService.handle(getAgriculturalProducerByUserIdQuery);
        if (agriculturalProducer.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var agriculturalProducerResource = AgriculturalProducerResourceFromEntityAssembler
                .toResourceFromEntity(agriculturalProducer.get());

        return ResponseEntity.ok(agriculturalProducerResource);
    }

    @GetMapping(value = "/distributor/{userId}")
    public ResponseEntity<DistributorResource> getDistributor(@PathVariable Long userId) {
        var getDistributorByUserIdQuery = new GetDistributorByUserIdAsyncQuery(userId);
        var distributor = distributorQueryService.handle(getDistributorByUserIdQuery);
        if (distributor.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var distributorResource = DistributorResourceFromEntityAssembler.toResourceFromEntity(distributor.get());

        return ResponseEntity.ok(distributorResource);
    }
}
