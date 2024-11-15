package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.transform;

import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddResourceToActivityCommand;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources.AddResourceToActivityResource;

public class AddResourceToActivityCommandFromResourceAssembler {
    public static AddResourceToActivityCommand toCommandFromResource(AddResourceToActivityResource resource) {
        return new AddResourceToActivityCommand(
                resource.resourceId(),
                resource.description(),
                resource.cost(),
                resource.quantity(),
                resource.activityId(),
                resource.agriculturalProcessId()
        );
    }
}
