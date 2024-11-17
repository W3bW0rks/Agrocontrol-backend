package com.agrocontrol.backend.store.application.internal.commandservices;

import com.agrocontrol.backend.payment.application.internal.outboundservices.ExternalProfileContextFacade;
import com.agrocontrol.backend.store.domain.model.aggregates.PaymentProduct;
import com.agrocontrol.backend.store.domain.model.aggregates.Product;
import com.agrocontrol.backend.store.domain.model.commands.CreatePaymentProductCommand;
import com.agrocontrol.backend.store.domain.services.PaymentProductCommandService;
import com.agrocontrol.backend.store.infrastructure.persistence.jpa.repositories.PaymentProductRepository;
import com.agrocontrol.backend.store.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentProductCommandServiceImpl implements PaymentProductCommandService {
    private final PaymentProductRepository paymentProductRepository;
    private final ProductRepository productRepository;
    private final ExternalProfileContextFacade externalProfileContextFacade;

    public PaymentProductCommandServiceImpl(PaymentProductRepository paymentProductRepository,
                                            ProductRepository productRepository,
                                            ExternalProfileContextFacade externalProfileContextFacade) {
        this.paymentProductRepository = paymentProductRepository;
        this.productRepository = productRepository;
        this.externalProfileContextFacade = externalProfileContextFacade;
    }

    @Override
    public Optional<PaymentProduct> handle(CreatePaymentProductCommand command) {
        var product = productRepository.findById(command.productId())
                .orElseThrow(() -> new IllegalArgumentException("The product not exist with id" + command.productId()));

        externalProfileContextFacade.exitsAgriculturalProducer(command.userId()); // Verify if the user is an agricultural producer

        Long distributorId = product.getUserId();
        String productName = product.getName();
        double totalCost = product.getUnitPrice() * command.quantityProduct();

        product.decreaseQuantity(command.quantityProduct());
        this.productRepository.save(product);

        var paymentProduct = new PaymentProduct(command, productName, distributorId, totalCost);

        var paymentProductCreated = paymentProductRepository.save(paymentProduct);

        if (!this.verifyExistenceProductByIdAndUserId(product, command.userId(), command.quantityProduct())) {
            var newProduct = new Product(command.userId(), product.getId(), product.getName(), product.getQuantityPerUnit(), product.getUnitPrice(),
                    command.quantityProduct(), product.getPhotoUrl());

            this.productRepository.save(newProduct);
        }

        return Optional.of(paymentProductCreated);
    }

    private boolean verifyExistenceProductByIdAndUserId(Product product, Long userId, Integer quantity) {
        var productExist = this.productRepository.findByPreviousIdAndUserId(product.getId(), userId)
                .orElse(null);

        if (productExist != null) {
            productExist.increaseQuantity(quantity);
            this.productRepository.save(productExist);
            return true;
        } else {
            return false;
        }
    }
}
