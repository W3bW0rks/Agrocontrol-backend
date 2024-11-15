package com.agrocontrol.backend.payment.application.internal.commandservices;

import com.agrocontrol.backend.payment.application.internal.outboundservices.ExternalProductContextFacade;
import com.agrocontrol.backend.payment.application.internal.outboundservices.ExternalProfileContextFacade;
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
    private final ExternalProfileContextFacade externalProfileContextFacade;

    public PaymentProductCommandServiceImpl(PaymentProductRepository paymentProductRepository,
                                            ExternalProductContextFacade externalProductContextFacade,
                                            ExternalProfileContextFacade externalProfileContextFacade) {
        this.paymentProductRepository = paymentProductRepository;
        this.externalProductContextFacade = externalProductContextFacade;
        this.externalProfileContextFacade = externalProfileContextFacade;
    }

    @Override
    public Optional<PaymentProduct> handle(CreatePaymentProductCommand command) {
        externalProductContextFacade.validateProductId(command.productId());
        Long distributorId = externalProductContextFacade.getUserIdByProductId(command.productId());
        double unitPriceProduct = externalProductContextFacade.getUnitPriceProductByProductId(command.productId());
        double totalCostProduct = unitPriceProduct * command.quantityProduct();
        externalProductContextFacade.reduceProductQuantity(command.productId(), command.quantityProduct());
        externalProfileContextFacade.exitsAgriculturalProducer(command.userId());
        var paymentProduct = new PaymentProduct(command, distributorId, totalCostProduct);
        var paymentProductCreated = paymentProductRepository.save(paymentProduct);
        return Optional.of(paymentProductCreated);
    }
}
