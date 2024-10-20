package com.agrocontrol.backend.subscription.interfaces.rest.transform;

import com.agrocontrol.backend.subscription.domain.model.commands.UpdatePlanTypeCommand;
import com.agrocontrol.backend.subscription.interfaces.rest.resources.UpdatePlantTypeResource;

public class UpdatePlanTypeCommandFromResourceAssembler {
    public UpdatePlanTypeCommand toCommandFromResource(UpdatePlantTypeResource resource) {
        return new UpdatePlanTypeCommand(
            resource.planType(),
            resource.paymentId()
        );
    }
}
