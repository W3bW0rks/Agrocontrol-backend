package com.agrocontrol.backend.profiles.application.acl;


import com.agrocontrol.backend.profiles.domain.model.commands.CreateAgriculturalProducerCommand;
import com.agrocontrol.backend.profiles.domain.model.commands.CreateDistributorCommand;
import com.agrocontrol.backend.profiles.domain.services.AgriculturalProducerCommandService;
import com.agrocontrol.backend.profiles.domain.services.DistributorCommandService;
import com.agrocontrol.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import org.springframework.stereotype.Service;

@Service
public class ProfileContextFacade {
    private final AgriculturalProducerCommandService agriculturalProducerCommandService;
    private final DistributorCommandService distributorCommandService;

    public ProfileContextFacade(AgriculturalProducerCommandService agriculturalProducerCommandService,
                                DistributorCommandService distributorCommandService) {
        this.agriculturalProducerCommandService = agriculturalProducerCommandService;
        this.distributorCommandService = distributorCommandService;
    }

    public Long createAgriculturalProducer(String fullName, String city, String country,
                                           String phone, String dni, Long userId) {

        CreateAgriculturalProducerCommand command = new CreateAgriculturalProducerCommand(
                fullName, city, country, phone, dni);

        var agriculturalProducer = agriculturalProducerCommandService.handle(command, userId);
        return agriculturalProducer.map(AuditableAbstractAggregateRoot::getId).orElse(0L);
    }

    public Long createDistributor(String fullName, String city, String country,
                                  String phone, String companyName, String ruc, Long userId) {
        CreateDistributorCommand command = new CreateDistributorCommand(
                fullName, city, country, phone, companyName, ruc);

        var distributor = distributorCommandService.handle(command, userId);
        return distributor.map(AuditableAbstractAggregateRoot::getId).orElse(0L);
    }
}