package com.agrocontrol.backend.profiles.application.internal.commandservices;

import com.agrocontrol.backend.profiles.domain.model.aggregates.AgriculturalProducer;
import com.agrocontrol.backend.profiles.domain.model.commands.CreateAgriculturalProducerCommand;
import com.agrocontrol.backend.profiles.domain.services.AgriculturalProducerCommandService;
import com.agrocontrol.backend.profiles.infrastructure.persistence.jpa.repositories.AgriculturalProducerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgriculturalProducerCommandServiceImpl implements AgriculturalProducerCommandService {
    private final AgriculturalProducerRepository agriculturalProducerRepository;

    public AgriculturalProducerCommandServiceImpl(AgriculturalProducerRepository agriculturalProducerRepository) {
        this.agriculturalProducerRepository = agriculturalProducerRepository;
    }

    @Override
    public Optional<AgriculturalProducer> handle(CreateAgriculturalProducerCommand command, Long userId) {

        AgriculturalProducer agriculturalProducer = new AgriculturalProducer(
                command.fullName(),
                command.city(),
                command.country(),
                command.phone(),
                command.dni(),
                userId
        );
        agriculturalProducerRepository.save(agriculturalProducer);
        return Optional.of(agriculturalProducer);
    }
}
