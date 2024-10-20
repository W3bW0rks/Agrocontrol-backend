package com.agrocontrol.backend.subscription.interfaces.rest.transform;

import com.agrocontrol.backend.subscription.domain.model.commands.CreatePaymentCommand;
import com.agrocontrol.backend.subscription.interfaces.rest.resources.CreatePaymentResource;

public class CreatePaymentCommandFromResourceAssembler {
    public static CreatePaymentCommand toCommandFromResource(CreatePaymentResource resource) {
        return new CreatePaymentCommand(
                resource.planType(),
                resource.userId(),
                resource.subscriptionId(),
                resource.startDate(),
                resource.renewalDate(),
                resource.status(),
                resource.cost()
        );
    }
}
