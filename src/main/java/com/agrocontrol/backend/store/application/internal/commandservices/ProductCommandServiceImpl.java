package com.agrocontrol.backend.store.application.internal.commandservices;

import com.agrocontrol.backend.store.application.internal.outboundservices.acl.ExternalProfileService;
import com.agrocontrol.backend.store.domain.model.aggregates.Product;
import com.agrocontrol.backend.store.domain.model.commands.*;
import com.agrocontrol.backend.store.domain.services.ProductCommandService;
import com.agrocontrol.backend.store.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductRepository productRepository;
    private final ExternalProfileService externalProfileService;

    public ProductCommandServiceImpl(ProductRepository productRepository, ExternalProfileService externalProfileService) {
        this.productRepository = productRepository;
        this.externalProfileService = externalProfileService;
    }

    @Override
    public Optional<Product> handle(CreateProductCommand command) {
        externalProfileService.existsDistributor(command.userId());

       if(command.quantity() <= 0 && command.unitPrice() <= 0)
           throw new IllegalArgumentException("Unit price must be greater than 0");

       var product =  new Product(command);
       var createdProduct = productRepository.save(product);

       return Optional.of(createdProduct);
    }

    @Override
    public Optional<Product> handle(UpdateProductCommand command) {

            var product = productRepository.findById(command.productId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            product.updateProduct(command);

            var updatedProduct = productRepository.save(product);

            return Optional.of(updatedProduct);
    }

    @Override
    public Optional<Product> handle(ChangeQuantityOfProductCommand command) {

            var product = productRepository.findById(command.productId())
                    .orElseThrow(() -> new IllegalArgumentException("Product not found"));

            product.changeQuantity(command);

            var updatedProduct = productRepository.save(product);

            return Optional.of(updatedProduct);
    }

    @Override
    public Optional<Product> handle(ReduceProductQuantityCommand command) {
        var product = productRepository.findById(command.productId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        product.decreaseQuantity(command.quantityToReduce());
        var updatedProduct = productRepository.save(product);

        return Optional.of(updatedProduct);
    }


    @Override
    public Optional<Product> handle(UpdateProductOwnerCommand command) {

        var product = productRepository.findById(command.productId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        product.updateProductOwner(command);

        var updatedProduct = productRepository.save(product);

        return Optional.of(updatedProduct);
    }
}
