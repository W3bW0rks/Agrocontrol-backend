package com.agrocontrol.backend.subscription.interfaces.rest.transform;

import com.agrocontrol.backend.subscription.domain.model.aggregates.Payment;
import com.agrocontrol.backend.subscription.interfaces.rest.resources.PaymentResource;

public class PaymentResourceFromEntityAssembler {
    public static PaymentResource toResourceFromEntity(Payment entity) {
        return new PaymentResource(
                entity.getId(),
                entity.getPlanType().name(),
                entity.getUserId(),
                entity.getSubscriptionId(),
                entity.getStartDate(),
                entity.getRenewalDate(),
                entity.getStatus(),
                entity.getCost()
        );
    }
}
