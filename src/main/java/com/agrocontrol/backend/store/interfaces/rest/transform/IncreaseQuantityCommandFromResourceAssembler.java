package com.agrocontrol.backend.store.interfaces.rest.transform;

import com.agrocontrol.backend.store.domain.model.commands.IncreaseQuantityCommand;
import com.agrocontrol.backend.store.interfaces.rest.resources.IncreaseQuantityResource;

public class IncreaseQuantityCommandFromResourceAssembler {

    public static IncreaseQuantityCommand toCommandFromResource (IncreaseQuantityResource resource) {
        return new IncreaseQuantityCommand(resource.quantity(), resource.productId());
    }
}
