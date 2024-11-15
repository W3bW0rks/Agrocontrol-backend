package com.agrocontrol.backend.payment.domain.model.commands;

import java.time.LocalDate;

public record CreatePaymentProductCommand(
        Long productId,
        Integer quantityProduct,
        Long userId
) {
}
