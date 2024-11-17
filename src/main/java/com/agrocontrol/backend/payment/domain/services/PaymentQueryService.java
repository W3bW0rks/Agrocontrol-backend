package com.agrocontrol.backend.payment.domain.services;

import com.agrocontrol.backend.payment.domain.model.aggregates.Payment;
import com.agrocontrol.backend.payment.domain.model.queries.GetPaymentByIdQuery;
import com.agrocontrol.backend.payment.domain.model.queries.GetPaymentBySubscriptionIdQuery;

import java.util.Optional;

public interface PaymentQueryService {
    Optional<Payment> handle(GetPaymentByIdQuery query);
    Optional<Payment> handle(GetPaymentBySubscriptionIdQuery query);

}
