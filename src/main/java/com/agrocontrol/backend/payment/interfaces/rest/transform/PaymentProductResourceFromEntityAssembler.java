package com.agrocontrol.backend.payment.interfaces.rest.transform;

import com.agrocontrol.backend.payment.domain.model.aggregates.PaymentProduct;
import com.agrocontrol.backend.payment.interfaces.rest.resources.PaymentProductResource;

public class PaymentProductResourceFromEntityAssembler {
    public static PaymentProductResource toResourceFromEntity(PaymentProduct paymentProduct) {
        return new PaymentProductResource(paymentProduct.getId(),paymentProduct.getDate(),
                paymentProduct.getProductId(), paymentProduct.getQuantityProduct(), paymentProduct.getTotalCost(),
                paymentProduct.getUserId(), paymentProduct.getOwnerProductId());
    }
}
