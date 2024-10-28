package com.agrocontrol.backend.subscription.domain.services;

import com.agrocontrol.backend.subscription.domain.model.aggregates.Subscription;
import com.agrocontrol.backend.subscription.domain.model.queries.*;

import java.util.Optional;

public interface SubscriptionQueryService {
    Optional<Subscription> handle(GetPaymentBySubscriptionIdQuery query);
    Optional<Subscription> handle(GetSubscriptionByUserIdQuery query);
    Optional<Subscription> handle(GetSubscriptionByIdQuery query);
}
