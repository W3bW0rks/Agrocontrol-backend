package com.agrocontrol.backend.subscription.domain.services;

import com.agrocontrol.backend.subscription.domain.model.aggregates.Payment;
import com.agrocontrol.backend.subscription.domain.model.queries.*;

import java.util.Optional;

public interface PaymentQueryService {
    Optional<Payment> handle(GetPaymentBySubscriptionIdQuery query);
    Optional<Payment> handle(GetPaymentByUserIdQuery query);
    Optional<Payment> handle(GetPaymentByIdQuery query);
}
