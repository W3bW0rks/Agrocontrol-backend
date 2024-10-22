package com.agrocontrol.backend.store.interfaces.rest.transform;

import com.agrocontrol.backend.store.domain.model.commands.UpdateProductOwnerCommand;
import com.agrocontrol.backend.store.interfaces.rest.resources.UpdateProductOwnerResource;

public class UpdateProductOwnerCommandFromResourceAssembler {

    public static UpdateProductOwnerCommand toCommandFromResource(UpdateProductOwnerResource resource) {
        return new UpdateProductOwnerCommand(resource.userId(), resource.productId());
    }
}
