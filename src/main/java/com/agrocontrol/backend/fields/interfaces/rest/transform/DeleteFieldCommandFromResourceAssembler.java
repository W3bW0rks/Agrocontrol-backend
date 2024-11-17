package com.agrocontrol.backend.fields.interfaces.rest.transform;

import com.agrocontrol.backend.fields.domain.model.commands.DeleteFieldCommand;
import com.agrocontrol.backend.fields.interfaces.rest.resources.DeleteFieldResource;

//TODO:Documentation
public class DeleteFieldCommandFromResourceAssembler {
    public static DeleteFieldCommand toCommandFromResource(DeleteFieldResource resource, Long id) {
        return new DeleteFieldCommand(
                id,
                resource.producerId()
        );
    }
}
