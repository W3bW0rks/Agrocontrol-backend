package com.agrocontrol.backend.subscription.domain.model.valueobjects;

public record UserId(Long userId) {
    public UserId {
        if (userId <= 0) {
            throw new IllegalArgumentException("User id must be greater than 0");
        }
    }

    public UserId() { this(0L); }
}
