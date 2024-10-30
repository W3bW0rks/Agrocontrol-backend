package com.agrocontrol.backend.fields.interfaces.rest.transform;

import com.agrocontrol.backend.fields.domain.model.commands.DeleteWorkerCommand;
import com.agrocontrol.backend.fields.interfaces.rest.resources.DeleteWorkerResource;

public class DeleteWorkerCommandFromResourceAssembler {
    public static DeleteWorkerCommand toCommandFromResource(DeleteWorkerResource resource) {
        return new DeleteWorkerCommand(
                resource.producerId(),
                resource.workerId()
        );
    }
}
