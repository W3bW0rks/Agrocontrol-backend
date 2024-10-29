package com.agrocontrol.backend.payment.interfaces.rest.transform;

import com.agrocontrol.backend.payment.domain.model.aggregates.Payment;
import com.agrocontrol.backend.payment.interfaces.rest.resources.PaymentResource;

public class PaymentResourceFromEntityAssembler {
public static PaymentResource toResourceFromEntity(Payment entity) {
        return new PaymentResource(
                entity.getId(),
                entity.getSubscriptionId(),
                entity.getDate().toString(),
                entity.getState(),
                entity.getCardHolderName(),
                entity.getCardNumber(),
                entity.getExpireDate(),
                entity.getCvv()
        );
    }
}

