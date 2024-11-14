package com.agrocontrol.backend.payment.interfaces.rest.transform;

import com.agrocontrol.backend.payment.domain.model.commands.CreatePaymentProductCommand;

public class CreatePaymentProductCommandFromResourceAssembler {
    public static CreatePaymentProductCommand toCommandFromResource(CreatePaymentProductCommand command) {
        return new CreatePaymentProductCommand(command.cardHolderName(),command.cardNumber(), command.ExpireDate(),
                command.cvv(), command.productId(), command.quantityProduct(), command.userId());
    }
}
