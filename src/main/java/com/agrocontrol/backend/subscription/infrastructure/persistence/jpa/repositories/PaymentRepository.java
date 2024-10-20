package com.agrocontrol.backend.subscription.infrastructure.persistence.jpa.repositories;

import com.agrocontrol.backend.subscription.domain.model.aggregates.Payment;
import com.agrocontrol.backend.subscription.domain.model.valueobjects.SubscriptionId;
import com.agrocontrol.backend.subscription.domain.model.valueobjects.UserId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findByUserId(UserId userId);
    Optional<Payment> findBySubscriptionId(SubscriptionId subscriptionId);
}
