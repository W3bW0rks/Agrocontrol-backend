package com.agrocontrol.backend.payment.application.internal.queryservices;


import com.agrocontrol.backend.payment.domain.model.aggregates.Payment;
import com.agrocontrol.backend.payment.domain.model.queries.GetPaymentByIdQuery;
import com.agrocontrol.backend.payment.domain.model.queries.GetPaymentBySubscriptionIdQuery;
import com.agrocontrol.backend.payment.domain.services.PaymentQueryService;
import com.agrocontrol.backend.payment.infrastructure.persistence.jpa.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentQueryServiceImpl implements PaymentQueryService {
    private final PaymentRepository paymentRepository;
    public PaymentQueryServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
}


    @Override
    public Optional<Payment> handle(GetPaymentByIdQuery query) {
        return paymentRepository.findById(query.id());
    }

    @Override
    public Optional<Payment> handle(GetPaymentBySubscriptionIdQuery query) {
        return paymentRepository.findBySubscriptionId(query.subscriptionId());
    }
}
