package com.agrocontrol.backend.subscription.domain.services;

import com.agrocontrol.backend.subscription.domain.model.aggregates.Payment;
import com.agrocontrol.backend.subscription.domain.model.commands.*;

import java.util.Optional;

public interface PaymentCommandService {
    Optional<Payment> handle(CreatePaymentCommand command);
    Optional<Payment> handle(RenewPaymentCommand command);
    Optional<Payment> handle(UpdatePlanTypeCommand command);
}
