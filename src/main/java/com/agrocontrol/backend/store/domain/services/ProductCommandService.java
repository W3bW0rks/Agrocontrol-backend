package com.agrocontrol.backend.store.domain.services;

import com.agrocontrol.backend.store.domain.model.aggregates.Product;
import com.agrocontrol.backend.store.domain.model.commands.*;

import java.util.Optional;

public interface ProductCommandService {
    Optional<Product> handle(CreateProductCommand command);
    Optional<Product> handle(UpdateProductCommand command);
    Optional<Product> handle(UpdateProductOwnerCommand command);
    Optional<Product> handle(ChangeQuantityOfProductCommand command);
    Optional<Product> handle(ReduceProductQuantityCommand command);
}
