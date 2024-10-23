package com.agrocontrol.backend.payment.interfaces.rest.transform;

import com.agrocontrol.backend.payment.domain.model.commands.CreatePaymentCommand;
import com.agrocontrol.backend.payment.interfaces.rest.resources.CreatePaymentResource;

public class CreatePaymentCommandFromResourceAssembler {
    public static CreatePaymentCommand toCommandFromSource(CreatePaymentResource resource) {
        return new CreatePaymentCommand(
                resource.subscriptionId(),
                resource.date(),
                resource.state(),
                resource.cardHolderName(),
                resource.cardNumber(),
                resource.ExpireDate(),
                resource.cvv()
        );
    }
}
