package com.agrocontrol.backend.store.interfaces.rest.transform;

import com.agrocontrol.backend.store.domain.model.commands.DecreaseQuantityCommand;
import com.agrocontrol.backend.store.interfaces.rest.resources.DecreaseQuantityResource;

public class DecreaseQuantityCommandFromResourceAssembler {

    public static DecreaseQuantityCommand toCommandFromResource(DecreaseQuantityResource resource) {
        return new DecreaseQuantityCommand(resource.quantity(), resource.productId());
    }
}
