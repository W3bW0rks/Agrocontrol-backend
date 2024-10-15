package com.agrocontrol.backend.agriculturalProcess.domain.services;

import com.agrocontrol.backend.agriculturalProcess.domain.model.aggregates.AgriculturalProcess;
import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddAgriculturalActivityCommand;
import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.CreateAgriculturalProcessCommand;

import java.util.Optional;

public interface AgriculturalProcessCommandService {
    Optional<AgriculturalProcess> handle(CreateAgriculturalProcessCommand command);
    Optional<AgriculturalProcess> handle(AddAgriculturalActivityCommand command);
}
