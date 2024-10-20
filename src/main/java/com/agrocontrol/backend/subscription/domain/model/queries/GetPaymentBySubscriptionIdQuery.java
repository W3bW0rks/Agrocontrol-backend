package com.agrocontrol.backend.subscription.domain.model.queries;

import com.agrocontrol.backend.subscription.domain.model.valueobjects.SubscriptionId;

public record GetPaymentBySubscriptionIdQuery(
    SubscriptionId subscriptionId
) {
}
