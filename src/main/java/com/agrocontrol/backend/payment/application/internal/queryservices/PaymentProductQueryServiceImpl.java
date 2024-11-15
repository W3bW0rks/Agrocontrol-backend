package com.agrocontrol.backend.payment.application.internal.queryservices;

import com.agrocontrol.backend.payment.domain.model.aggregates.PaymentProduct;
import com.agrocontrol.backend.payment.domain.model.queries.GetPaymentProductByOwnerProductId;
import com.agrocontrol.backend.payment.domain.services.PaymentProductQueryService;
import com.agrocontrol.backend.payment.infrastructure.persistence.jpa.repositories.PaymentProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentProductQueryServiceImpl implements PaymentProductQueryService {
    private final PaymentProductRepository paymentProductRepository;

    public PaymentProductQueryServiceImpl(PaymentProductRepository paymentProductRepository) {
        this.paymentProductRepository = paymentProductRepository;
    }

    @Override
    public List<PaymentProduct> handle(GetPaymentProductByOwnerProductId query) {
        if(!paymentProductRepository.existsByOwnerProductId(query.ownerProductId())) {
            throw new IllegalArgumentException("The OwnerProductId not exist with id" + query.ownerProductId());
        }
        return paymentProductRepository.findByOwnerProductId(query.ownerProductId());
    }
}
