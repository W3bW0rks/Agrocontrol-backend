package com.agrocontrol.backend.store.application.acl;

import com.agrocontrol.backend.store.domain.model.aggregates.Product;
import com.agrocontrol.backend.store.domain.model.commands.ReduceProductQuantityCommand;
import com.agrocontrol.backend.store.domain.model.queries.CheckProductByIdQuery;
import com.agrocontrol.backend.store.domain.model.queries.GetNameByIdQuery;
import com.agrocontrol.backend.store.domain.model.queries.GetUnitPriceByIdQuery;
import com.agrocontrol.backend.store.domain.model.queries.GetUserIdByIdQuery;
import com.agrocontrol.backend.store.domain.services.ProductCommandService;
import com.agrocontrol.backend.store.domain.services.ProductQueryService;
import com.agrocontrol.backend.store.interfaces.acl.ProductContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ProductContextFacadeImpl implements ProductContextFacade {
    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    public ProductContextFacadeImpl(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

    @Override
    public Double getUnitPriceByProductId(Long productId) {
        return productQueryService.handle(new GetUnitPriceByIdQuery(productId))
                .orElse((double) 0);
    }

    @Override
    public String getProductNameByProductId(Long productId) {
        return productQueryService.handle(new GetNameByIdQuery(productId))
                .map(Product::getName)
                .orElse("");
    }

    @Override
    public Long getUserIdByProductId(Long productId) {
        return productQueryService.handle(new GetUserIdByIdQuery(productId))
                .orElse(0L);
    }

    @Override
    public boolean ValidateProductId(Long productId) {
        return productQueryService.handle(new CheckProductByIdQuery(productId));
    }


    @Override
    public boolean reduceProductQuantity(Long productId, Integer quantityToReduce) {
        var command = new ReduceProductQuantityCommand(productId, quantityToReduce);
        var updatedProduct = productCommandService.handle(command);

        // Verificar que la cantidad se redujo correctamente
        return updatedProduct.isPresent() && updatedProduct.get().getQuantity() >= 0;
    }
}
