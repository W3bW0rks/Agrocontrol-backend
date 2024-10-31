package com.agrocontrol.backend.profiles.domain.services;



import com.agrocontrol.backend.profiles.domain.model.aggregates.AgriculturalProducer;
import com.agrocontrol.backend.profiles.domain.model.commands.CreateAgriculturalProducerCommand;

import java.util.Optional;

public interface AgriculturalProducerCommandService {
    Optional<AgriculturalProducer> handle(CreateAgriculturalProducerCommand command, Long userId);
}
