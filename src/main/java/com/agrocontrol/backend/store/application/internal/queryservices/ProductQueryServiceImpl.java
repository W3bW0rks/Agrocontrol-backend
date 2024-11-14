package com.agrocontrol.backend.store.application.internal.queryservices;

import com.agrocontrol.backend.iam.domain.model.queries.GetUserByIdQuery;
import com.agrocontrol.backend.store.application.internal.outboundservices.acl.ExternalProfileService;
import com.agrocontrol.backend.store.domain.model.aggregates.Product;
import com.agrocontrol.backend.store.domain.model.queries.*;
import com.agrocontrol.backend.store.domain.services.ProductQueryService;
import com.agrocontrol.backend.store.infrastructure.persistence.jpa.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryServiceImpl implements ProductQueryService {

    private final ProductRepository productRepository;
    private final ExternalProfileService externalProfileService;

    public ProductQueryServiceImpl(ProductRepository productRepository, ExternalProfileService externalProfileService) {
        this.productRepository = productRepository;
        this.externalProfileService = externalProfileService;
    }

    @Override
    public List<Product> handle(GetProductByUserIdQuery query) {
        externalProfileService.existsDistributor(query.userId());
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

    @Override
    public Optional<Product> handle(GetQuantityByIdQuery query) {
        return productRepository.findQuantityById(query.id());
    }

    @Override
    public Optional<Product> handle(GetNameByIdQuery query) {
        return productRepository.findNameById(query.id());
    }

    @Override
    public Optional<Product> handle(GetUserIdByIdQuery query) {
        return productRepository.findUserIdById(query.id());
    }

    @Override
    public boolean handle(CheckProductByIdQuery query) {
        return productRepository.existsById(query.id());
    }


}
