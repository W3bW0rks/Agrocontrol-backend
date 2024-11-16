package com.agrocontrol.backend.store.domain.services;

import com.agrocontrol.backend.store.domain.model.aggregates.PaymentProduct;
import com.agrocontrol.backend.store.domain.model.queries.GetPaymentProductByOwnerProductId;
import com.agrocontrol.backend.store.domain.model.queries.GetPaymentProductByUserIdQuery;

import java.util.List;

public interface PaymentProductQueryService {
    List<PaymentProduct> handle(GetPaymentProductByOwnerProductId query);
    List<PaymentProduct> handle(GetPaymentProductByUserIdQuery query);
}
