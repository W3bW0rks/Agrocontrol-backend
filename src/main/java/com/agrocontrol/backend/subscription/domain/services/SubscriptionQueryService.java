package com.agrocontrol.backend.subscription.domain.services;

import com.agrocontrol.backend.subscription.domain.model.aggregates.Subscription;
import com.agrocontrol.backend.subscription.domain.model.queries.GetSubscriptionByIdQuery;
import com.agrocontrol.backend.subscription.domain.model.queries.GetSubscriptionByUserIdQuery;

import java.util.Optional;

public interface SubscriptionQueryService {
    Optional<Subscription> handle(GetSubscriptionByUserIdQuery query);
    Optional<Subscription> handle(GetSubscriptionByIdQuery query);
}
