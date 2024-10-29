package com.agrocontrol.backend.store.interfaces.rest.transform;

import com.agrocontrol.backend.store.domain.model.commands.UpdateProductCommand;
import com.agrocontrol.backend.store.interfaces.rest.resources.UpdateProductResource;

public class UpdateProductCommandFromResourceAssembler {
    public static UpdateProductCommand toCommandFromResource(UpdateProductResource resource, Long id) {
        return new UpdateProductCommand(
                resource.name(),
                resource.quantityPerUnit(),
                resource.unitPrice(),
                resource.quantity(),
                resource.photoUrl(),
                id
        );
    }
}
