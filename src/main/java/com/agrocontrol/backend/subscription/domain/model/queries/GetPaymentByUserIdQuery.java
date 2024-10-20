package com.agrocontrol.backend.subscription.domain.model.queries;

import com.agrocontrol.backend.subscription.domain.model.valueobjects.UserId;

public record GetPaymentByUserIdQuery(
        UserId userId
) {
}
