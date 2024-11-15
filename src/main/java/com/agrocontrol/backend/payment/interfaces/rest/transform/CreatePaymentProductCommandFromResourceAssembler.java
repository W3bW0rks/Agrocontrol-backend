package com.agrocontrol.backend.payment.interfaces.rest.transform;

import com.agrocontrol.backend.payment.domain.model.commands.CreatePaymentProductCommand;
import com.agrocontrol.backend.payment.interfaces.rest.resources.CreatePaymentProductResource;

public class CreatePaymentProductCommandFromResourceAssembler {
    public static CreatePaymentProductCommand toCommandFromResource(CreatePaymentProductResource command) {
        return new CreatePaymentProductCommand( command.productId(), command.quantityProduct(),
                command.userId());
    }
}
