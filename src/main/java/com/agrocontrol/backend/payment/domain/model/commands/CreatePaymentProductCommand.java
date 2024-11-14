package com.agrocontrol.backend.payment.domain.model.commands;

import java.time.LocalDate;

public record CreatePaymentProductCommand(
        String cardHolderName,
        String cardNumber,
        String ExpireDate,
        String cvv,
        Long productId,
        Integer quantityProduct,
        Long userId
) {
}
