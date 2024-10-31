package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.transform;

import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddSeedingToProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources.AddSeedingToProcessResource;

public class AddSeedingToProcessCommandFromResourceAssembler {
    public static AddSeedingToProcessCommand toCommandFromResource(AddSeedingToProcessResource resource) {
        return new AddSeedingToProcessCommand(
                resource.date(),
                resource.plantType(),
                resource.quantityPlanted(),
                resource.agriculturalProcessId()
        );
    }
}
