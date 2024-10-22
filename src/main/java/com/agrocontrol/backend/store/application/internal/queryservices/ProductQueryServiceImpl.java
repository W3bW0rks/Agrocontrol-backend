package com.agrocontrol.backend.store.application.internal.queryservices;

import com.agrocontrol.backend.store.domain.model.aggregates.Product;
import com.agrocontrol.backend.store.domain.model.queries.GetProductByIdQuery;
import com.agrocontrol.backend.store.domain.model.queries.GetProductByNameQuery;
import com.agrocontrol.backend.store.domain.model.queries.GetProductByUserIdQuery;
import com.agrocontrol.backend.store.domain.services.ProductQueryService;
import com.agrocontrol.backend.store.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;

    public ProductQueryServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> handle(GetProductByUserIdQuery query) {
        return productRepository.findByUserId(query.userId());
    }

    @Override
    public Optional<Product> handle(GetProductByIdQuery query) {
        return productRepository.findById(query.productId());
    }

    @Override
    public Optional<Product> handle(GetProductByNameQuery query) {
        return productRepository.findByName(query.name());
    }
}
