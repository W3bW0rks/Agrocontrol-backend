package com.agrocontrol.backend.subscription.application.internal.queryservices;

import com.agrocontrol.backend.subscription.domain.model.aggregates.Payment;
import com.agrocontrol.backend.subscription.domain.model.queries.GetPaymentByIdQuery;
import com.agrocontrol.backend.subscription.domain.model.queries.GetPaymentBySubscriptionIdQuery;
import com.agrocontrol.backend.subscription.domain.model.queries.GetPaymentByUserIdQuery;
import com.agrocontrol.backend.subscription.domain.services.PaymentQueryService;
import com.agrocontrol.backend.subscription.infrastructure.persistence.jpa.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentQueryServiceImpl implements PaymentQueryService {
    private final PaymentRepository paymentRepository;

    public PaymentQueryServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Optional<Payment> handle(GetPaymentBySubscriptionIdQuery query) {
        return this.paymentRepository.findBySubscriptionId(query.subscriptionId());
    }

    @Override
    public Optional<Payment> handle(GetPaymentByUserIdQuery query) {
        return this.paymentRepository.findByUserId(query.userId());
    }

    @Override
    public Optional<Payment> handle(GetPaymentByIdQuery query) {
        return this.paymentRepository.findById(query.id());
    }
}
