package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.transform;

import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.FinishAgriculturalProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources.FinishAgriculturalProcessResource;

public class FinishAgriculturalProcessCommandFromResourceAssembler {
    public static FinishAgriculturalProcessCommand toCommandFromResource(FinishAgriculturalProcessResource resource) {
        return new FinishAgriculturalProcessCommand(
                resource.agriculturalProcessId()
        );
    }
}
