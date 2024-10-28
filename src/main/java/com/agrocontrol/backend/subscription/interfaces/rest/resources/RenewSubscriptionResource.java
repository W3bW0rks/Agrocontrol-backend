package com.agrocontrol.backend.subscription.interfaces.rest.resources;

import java.time.LocalDate;

public record RenewSubscriptionResource(
        LocalDate renewalDate
) {
}
