package com.agrocontrol.backend.payment.interfaces.rest.transform;

import com.agrocontrol.backend.payment.domain.model.aggregates.PaymentProduct;
import com.agrocontrol.backend.payment.interfaces.rest.resources.PaymentProductHistoryResource;

public class PaymentProductHistoryResourceFromEntityAssembler {
    public static PaymentProductHistoryResource toResourceFromEntity(PaymentProduct paymentProduct) {
        return new PaymentProductHistoryResource(paymentProduct.getId(), paymentProduct.getDate(),
                paymentProduct.getProductId(), paymentProduct.getQuantityProduct(), paymentProduct.getTotalCost(),
                paymentProduct.getUserId(), paymentProduct.getOwnerProductId());
    }
}
