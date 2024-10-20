package com.agrocontrol.backend.subscription.interfaces.rest.transform;

import com.agrocontrol.backend.subscription.domain.model.commands.RenewPaymentCommand;
import com.agrocontrol.backend.subscription.interfaces.rest.resources.RenewPaymentResource;

public class RenewPaymentCommandFromResourceAssembler {
    public static RenewPaymentCommand toCommandFromResource(RenewPaymentResource resource, Long id) {
        return new RenewPaymentCommand(
            resource.renewalDate(),
            id
        );
    }
}
