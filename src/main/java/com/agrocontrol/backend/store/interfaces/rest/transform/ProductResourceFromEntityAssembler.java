package com.agrocontrol.backend.store.interfaces.rest.transform;

import com.agrocontrol.backend.store.domain.model.aggregates.Product;
import com.agrocontrol.backend.store.interfaces.rest.resources.ProductResource;

public class ProductResourceFromEntityAssembler {

    public static ProductResource toResourceFromEntity(Product product) {
        return new ProductResource(product.getId(), product.getUserId(), product.getName(), product.getQuantityPerUnit(),
                product.getUnitPrice(), product.getQuantity(), product.getPhotoUrl());
    }
}
