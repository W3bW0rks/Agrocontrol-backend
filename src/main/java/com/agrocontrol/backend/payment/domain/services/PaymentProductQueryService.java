package com.agrocontrol.backend.payment.domain.services;

import com.agrocontrol.backend.payment.domain.model.aggregates.PaymentProduct;
import com.agrocontrol.backend.payment.domain.model.queries.GetPaymentProductByOwnerProductId;

import java.util.List;
import java.util.Optional;

public interface PaymentProductQueryService {
    Optional<List<PaymentProduct>> handle(GetPaymentProductByOwnerProductId query);
}
