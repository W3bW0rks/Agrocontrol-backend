package com.agrocontrol.backend.store.domain.services;

import com.agrocontrol.backend.store.domain.model.aggregates.Product;
import com.agrocontrol.backend.store.domain.model.queries.GetProductByIdQuery;
import com.agrocontrol.backend.store.domain.model.queries.GetProductByNameQuery;
import com.agrocontrol.backend.store.domain.model.queries.GetProductByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface ProductQueryService {
    List<Product> handle(GetProductByUserIdQuery query);
    Optional<Product> handle(GetProductByIdQuery query);
    Optional<Product> handle(GetProductByNameQuery query);
}
