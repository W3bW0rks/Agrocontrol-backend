package com.agrocontrol.backend.payment.infrastructure.persistence.jpa.repositories;

import com.agrocontrol.backend.payment.domain.model.aggregates.Payment;
import com.agrocontrol.backend.payment.domain.model.valueobjects.SubscriptionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Optional<Payment> findBySubscriptionId(SubscriptionId subscriptionId);
}
