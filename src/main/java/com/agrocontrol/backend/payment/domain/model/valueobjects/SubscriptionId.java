package com.agrocontrol.backend.payment.domain.model.valueobjects;


import jakarta.persistence.Embeddable;

@Embeddable

public record SubscriptionId(Long subscriptionId) {
    public SubscriptionId {
        if (subscriptionId < 0) {
            throw new IllegalArgumentException("SubscriptionId must be greater than 0");
        }
    }

    public SubscriptionId() {
        this(0L);
    }
}
