package com.agrocontrol.backend.payment.interfaces.rest.resources;

import java.time.LocalDate;

public record PaymentProductResource(
        Long id,
        LocalDate date,
        Long productId,
        Integer quantityProduct,
        Double totalCostProduct,
        Long userId,
        Long ownerProductId
) {
}
