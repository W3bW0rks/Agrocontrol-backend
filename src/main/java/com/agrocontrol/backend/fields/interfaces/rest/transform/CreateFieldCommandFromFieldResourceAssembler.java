package com.agrocontrol.backend.fields.interfaces.rest.transform;

import com.agrocontrol.backend.fields.domain.model.commands.CreateFieldCommand;
import com.agrocontrol.backend.fields.interfaces.rest.resources.CreateFieldResource;
//TODO: Documentation
public class CreateFieldCommandFromFieldResourceAssembler {
    public static CreateFieldCommand toCommandFromFieldResource(CreateFieldResource resource) {
        return new CreateFieldCommand(
                resource.producerId(),
                resource.fieldName(),
                resource.location(),
                resource.size()
        );
    }

}
