package com.agrocontrol.backend.fields.interfaces.rest.transform;

import com.agrocontrol.backend.fields.domain.model.commands.UpdateFieldCommand;
import com.agrocontrol.backend.fields.interfaces.rest.resources.UpdateFieldResource;
//TODO: Documentation
public class UpdateFieldCommandFromResourceAssembler {
    public static UpdateFieldCommand toCommandFromResource(UpdateFieldResource resource, Long id) {
        return new UpdateFieldCommand(
                resource.name(),
                resource.location(),
                resource.size(),
                id,
                resource.producerId()
        );
    }
}
