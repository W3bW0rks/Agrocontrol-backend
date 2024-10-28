package com.agrocontrol.backend.subscription.interfaces.rest.transform;

import com.agrocontrol.backend.subscription.domain.model.aggregates.Subscription;
import com.agrocontrol.backend.subscription.interfaces.rest.resources.SubscriptionResource;

public class SubscriptionResourceFromEntityAssembler {
    public static SubscriptionResource toResourceFromEntity(Subscription entity) {
        return new SubscriptionResource(
                entity.getId(),
                entity.getPlanType().name(),
                entity.getUserId(),
                entity.getStartDate(),
                entity.getRenewalDate(),
                entity.getStatus(),
                entity.getCost()
        );
    }
}
