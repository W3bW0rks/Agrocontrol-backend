package com.agrocontrol.backend.fields.interfaces.rest.transform;

import com.agrocontrol.backend.fields.domain.model.commands.CreateWorkerCommand;
import com.agrocontrol.backend.fields.interfaces.rest.resources.CreateWorkerResource;

public class CreateWorkerFromWorkerResourceAssembler {
    public static CreateWorkerCommand toCommandFromWorkerResource(CreateWorkerResource resource) {
        return new CreateWorkerCommand(
                resource.producerId(),
                resource.fullName(),
                resource.documentNumber()
        );
    }
}
