package com.agrocontrol.backend.payment.application.internal.commandservices;

import com.agrocontrol.backend.payment.application.internal.outboundservices.ExternalProductContextFacade;
import com.agrocontrol.backend.payment.domain.model.aggregates.PaymentProduct;
import com.agrocontrol.backend.payment.domain.model.commands.CreatePaymentProductCommand;
import com.agrocontrol.backend.payment.domain.services.PaymentProductCommandService;
import com.agrocontrol.backend.payment.infrastructure.persistence.jpa.repositories.PaymentProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentProductCommandServiceImpl implements PaymentProductCommandService {
    private final PaymentProductRepository paymentProductRepository;
    private final ExternalProductContextFacade externalProductContextFacade;

    public PaymentProductCommandServiceImpl(PaymentProductRepository paymentProductRepository, ExternalProductContextFacade externalProductContextFacade) {
        this.paymentProductRepository = paymentProductRepository;
        this.externalProductContextFacade = externalProductContextFacade;
    }

    @Override
    public Optional<PaymentProduct> handle(CreatePaymentProductCommand command) {
        externalProductContextFacade.validateProductId(command.productId());
        Long distributorId = externalProductContextFacade.getUserIdByProductId(command.productId());
        externalProductContextFacade.reduceProductQuantity(command.productId(), command.quantityProduct());
        var paymentProduct = new PaymentProduct(command, distributorId);
        var paymentProductCreated = paymentProductRepository.save(paymentProduct);
        return Optional.of(paymentProductCreated);
    }
}
