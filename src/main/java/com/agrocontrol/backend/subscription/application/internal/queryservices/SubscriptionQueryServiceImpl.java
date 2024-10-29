package com.agrocontrol.backend.subscription.application.internal.queryservices;

import com.agrocontrol.backend.subscription.domain.model.aggregates.Subscription;
import com.agrocontrol.backend.subscription.domain.model.queries.GetSubscriptionByIdQuery;
import com.agrocontrol.backend.subscription.domain.model.queries.GetSubscriptionByUserIdQuery;
import com.agrocontrol.backend.subscription.domain.services.SubscriptionQueryService;
import com.agrocontrol.backend.subscription.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionQueryServiceImpl implements SubscriptionQueryService {
    private final SubscriptionRepository paymentRepository;

    public SubscriptionQueryServiceImpl(SubscriptionRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }


    @Override
    public Optional<Subscription> handle(GetSubscriptionByUserIdQuery query) {
        return this.paymentRepository.findByUserId(query.userId());
    }

    @Override
    public Optional<Subscription> handle(GetSubscriptionByIdQuery query) {
        return this.paymentRepository.findById(query.id());
    }
}
