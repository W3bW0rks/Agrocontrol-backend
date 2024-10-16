package com.agrocontrol.backend.agriculturalProcess.application.internal.commandservices;

import com.agrocontrol.backend.agriculturalProcess.domain.model.aggregates.AgriculturalProcess;
import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddIrrigationToProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddSeedingToProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.CreateAgriculturalProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.FinishAgriculturalProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.domain.services.AgriculturalProcessCommandService;
import com.agrocontrol.backend.agriculturalProcess.infrastructure.persistence.jpa.repositories.AgriculturalProcessRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgriculturalProcessCommandServiceImpl implements AgriculturalProcessCommandService {
    private final AgriculturalProcessRepository agriculturalProcessRepository;

    public AgriculturalProcessCommandServiceImpl(AgriculturalProcessRepository agriculturalProcessRepository) {
        this.agriculturalProcessRepository = agriculturalProcessRepository;
    }

    @Override
    public Optional<AgriculturalProcess> handle(CreateAgriculturalProcessCommand command) {
        var agriculturalProcess = new AgriculturalProcess(command);
        var createdAgriculturalProcess = agriculturalProcessRepository.save(agriculturalProcess);
        return Optional.of(createdAgriculturalProcess);
    }

    @Override
    public Optional<AgriculturalProcess> handle(AddSeedingToProcessCommand command) {
        AgriculturalProcess agriculturalProcess = this.agriculturalProcessRepository.findById(command.agriculturalProcessId())
                .orElseThrow(() -> new IllegalArgumentException("Agricultural Process not found"));

        agriculturalProcess.addActivity(command);
        var updatedAgriculturalProcess = agriculturalProcessRepository.save(agriculturalProcess);
        return Optional.of(updatedAgriculturalProcess);
    }

    @Override
    public Optional<AgriculturalProcess> handle(AddIrrigationToProcessCommand command) {
        AgriculturalProcess agriculturalProcess = this.agriculturalProcessRepository.findById(command.agriculturalProcessId())
                .orElseThrow(() -> new IllegalArgumentException("Agricultural Process not found"));

        agriculturalProcess.addActivity(command);
        var updatedAgriculturalProcess = agriculturalProcessRepository.save(agriculturalProcess);
        return Optional.of(updatedAgriculturalProcess);
    }

    @Override
    public Optional<AgriculturalProcess> handle(FinishAgriculturalProcessCommand command) {
        AgriculturalProcess agriculturalProcess = this.agriculturalProcessRepository.findById(command.agriculturalProcessId())
                .orElseThrow(() -> new IllegalArgumentException("Agricultural Process not found"));

        agriculturalProcess.finish();
        var updatedAgriculturalProcess = agriculturalProcessRepository.save(agriculturalProcess);
        return Optional.of(updatedAgriculturalProcess);
    }
}
