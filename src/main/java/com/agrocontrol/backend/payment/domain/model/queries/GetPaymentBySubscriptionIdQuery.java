package com.agrocontrol.backend.payment.domain.model.queries;

import com.agrocontrol.backend.payment.domain.model.valueobjects.SubscriptionId;

public record GetPaymentBySubscriptionIdQuery(SubscriptionId subscriptionId) {

}
