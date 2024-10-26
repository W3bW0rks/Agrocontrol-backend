package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.transform;

import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddHarvestToProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources.AddHarvestToProcessResource;

public class AddHarvestToProcessCommandFromResourceAssembler {
    public static AddHarvestToProcessCommand toCommandFromResource(AddHarvestToProcessResource resource) {
        return new AddHarvestToProcessCommand(
                resource.date(),
                resource.quantityInKg(),
                resource.pricePerKg(),
                resource.agriculturalProcessId()
        );
    }
}
