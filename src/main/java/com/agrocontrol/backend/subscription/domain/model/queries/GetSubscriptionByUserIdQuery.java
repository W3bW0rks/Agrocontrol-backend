package com.agrocontrol.backend.subscription.domain.model.queries;

import com.agrocontrol.backend.subscription.domain.model.valueobjects.UserId;

public record GetSubscriptionByUserIdQuery(
        UserId userId
) {
}
