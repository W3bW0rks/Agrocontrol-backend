package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.transform;

import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.CreateAgriculturalProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources.CreateAgriculturalProcessResource;

public class CreateAgriculturalProcessCommandFromResourceAssembler {
    public static CreateAgriculturalProcessCommand toCommandFromResource(CreateAgriculturalProcessResource resource) {
        return new CreateAgriculturalProcessCommand(resource.fieldId());
    }
}
