package com.agrocontrol.backend.payment.domain.services;

import com.agrocontrol.backend.payment.domain.model.aggregates.PaymentProduct;
import com.agrocontrol.backend.payment.domain.model.commands.CreatePaymentProductCommand;

import java.util.Optional;

public interface PaymentProductCommandService {
    Optional<PaymentProduct> handle(CreatePaymentProductCommand command);
}
