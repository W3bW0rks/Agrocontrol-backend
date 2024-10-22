package com.agrocontrol.backend.store.domain.services;

import com.agrocontrol.backend.store.domain.model.aggregates.Product;
import com.agrocontrol.backend.store.domain.model.commands.CreateProductCommand;
import com.agrocontrol.backend.store.domain.model.commands.DecreaseQuantityCommand;
import com.agrocontrol.backend.store.domain.model.commands.IncreaseQuantityCommand;
import com.agrocontrol.backend.store.domain.model.commands.UpdateProductOwnerCommand;

import java.util.Optional;

public interface ProductCommandService {
    Optional<Product> handle(CreateProductCommand command);
    Optional<Product> handle(DecreaseQuantityCommand command);
    Optional<Product> handle(IncreaseQuantityCommand command);
    Optional<Product> handle(UpdateProductOwnerCommand command);
}
