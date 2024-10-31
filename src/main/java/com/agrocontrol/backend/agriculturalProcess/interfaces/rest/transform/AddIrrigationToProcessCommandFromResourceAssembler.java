package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.transform;

import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddIrrigationToProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources.AddIrrigationToProcessResource;

public class AddIrrigationToProcessCommandFromResourceAssembler {
    public static AddIrrigationToProcessCommand toCommandFromResource(AddIrrigationToProcessResource resource) {
        return new AddIrrigationToProcessCommand(
                resource.date(),
                resource.hoursIrrigated(),
                resource.agriculturalProcessId()
        );
    }
}
