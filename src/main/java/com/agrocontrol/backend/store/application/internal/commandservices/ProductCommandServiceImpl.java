package com.agrocontrol.backend.store.application.internal.commandservices;

import com.agrocontrol.backend.store.domain.model.aggregates.Product;
import com.agrocontrol.backend.store.domain.model.commands.CreateProductCommand;
import com.agrocontrol.backend.store.domain.model.commands.DecreaseQuantityCommand;
import com.agrocontrol.backend.store.domain.model.commands.IncreaseQuantityCommand;
import com.agrocontrol.backend.store.domain.model.commands.UpdateProductOwnerCommand;
import com.agrocontrol.backend.store.domain.services.ProductCommandService;
import com.agrocontrol.backend.store.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductCommandServiceImpl implements ProductCommandService {

    private final ProductRepository productRepository;

    public ProductCommandServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Product> handle(CreateProductCommand command) {

       if(command.quantity() <= 0 && command.unitPrice() <= 0)
           throw new IllegalArgumentException("Unit price must be greater than 0");

       var product =  new Product(command);
       var createdProduct = productRepository.save(product);

       return Optional.of(createdProduct);
    }

    @Override
    public Optional<Product> handle(DecreaseQuantityCommand command) {

        if (command.quantity() <= 0)
            throw new IllegalArgumentException("Quantity must be greater than 0");

        var product = productRepository.findById(command.productId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        product.decreaseQuantity(command);

        var updatedProduct = productRepository.save(product);

        return Optional.of(updatedProduct);
    }

    @Override
    public Optional<Product> handle(IncreaseQuantityCommand command) {

        if (command.quantity() <= 0)
            throw new IllegalArgumentException("Quantity must be greater than 0");

        var product = productRepository.findById(command.productId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        product.increaseQuantity(command);

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
