package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.transform;

import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.ExecuteAgriculturalActivityActionCommand;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources.ExecuteAgriculturalActivityActionResource;

public class ExecuteAgriculturalActivityActionCommandFromResourceAssembler {
    public static ExecuteAgriculturalActivityActionCommand toCommandFromResource(ExecuteAgriculturalActivityActionResource resource) {
        return new ExecuteAgriculturalActivityActionCommand(
                resource.agriculturalProcessId(),
                resource.activityId(),
                resource.action()
        );
    }
}
