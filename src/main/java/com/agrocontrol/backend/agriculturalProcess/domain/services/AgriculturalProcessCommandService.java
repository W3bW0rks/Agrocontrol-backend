package com.agrocontrol.backend.agriculturalProcess.domain.services;

import com.agrocontrol.backend.agriculturalProcess.domain.model.aggregates.AgriculturalProcess;
import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.*;
import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.AgriculturalActivity;

import java.util.Optional;

public interface AgriculturalProcessCommandService {
    Optional<AgriculturalProcess> handle(CreateAgriculturalProcessCommand command);
    Optional<AgriculturalProcess> handle(FinishAgriculturalProcessCommand command);

    Optional<AgriculturalActivity> handle(AddSeedingToProcessCommand command);
    Optional<AgriculturalActivity> handle(AddIrrigationToProcessCommand command);
    Optional<AgriculturalActivity> handle(AddCropTreatmentToProcessCommand command);
    Optional<AgriculturalActivity> handle(AddHarvestToProcessCommand command);
    Optional<AgriculturalActivity> handle(ExecuteAgriculturalActivityActionCommand command);
    Optional<AgriculturalActivity> handle(AddResourceToActivityCommand command);
}
