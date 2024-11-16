package com.agrocontrol.backend.store.domain.services;

import com.agrocontrol.backend.store.domain.model.aggregates.PaymentProduct;
import com.agrocontrol.backend.store.domain.model.commands.CreatePaymentProductCommand;

import java.util.Optional;

public interface PaymentProductCommandService {
    Optional<PaymentProduct> handle(CreatePaymentProductCommand command);
}
