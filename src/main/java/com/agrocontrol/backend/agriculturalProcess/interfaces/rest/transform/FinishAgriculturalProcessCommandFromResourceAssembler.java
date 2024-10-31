package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.transform;

import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.FinishAgriculturalProcessCommand;

public class FinishAgriculturalProcessCommandFromResourceAssembler {
    public static FinishAgriculturalProcessCommand toCommandFromResource(Long id) {
        return new FinishAgriculturalProcessCommand(
                id
        );
    }
}
