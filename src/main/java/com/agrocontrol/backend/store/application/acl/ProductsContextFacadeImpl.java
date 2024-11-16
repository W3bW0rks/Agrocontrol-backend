package com.agrocontrol.backend.store.application.acl;

import com.agrocontrol.backend.store.domain.model.queries.GetNameByIdQuery;
import com.agrocontrol.backend.store.domain.services.ProductQueryService;
import com.agrocontrol.backend.store.interfaces.acl.ProductsContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductsContextFacadeImpl implements ProductsContextFacade {
    private final ProductQueryService productQueryService;

    public ProductsContextFacadeImpl(ProductQueryService productQueryService) {
        this.productQueryService = productQueryService;
    }

    @Override
    public Optional<String> getProductNameById(Long productId) {
        var query = new GetNameByIdQuery(productId);
        return productQueryService.handle(query);
    }
}
