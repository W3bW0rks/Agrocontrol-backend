package com.agrocontrol.backend.store.application.acl;

import com.agrocontrol.backend.store.domain.model.commands.ChangeQuantityOfProductCommand;
import com.agrocontrol.backend.store.domain.services.ProductCommandService;
import com.agrocontrol.backend.store.domain.services.ProductQueryService;
import com.agrocontrol.backend.store.infrastructure.persistence.jpa.repositories.ProductRepository;
import com.agrocontrol.backend.store.interfaces.acl.ProductsContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ProductsContextFacadeImpl implements ProductsContextFacade {
    private final ProductQueryService productQueryService;
    private final ProductCommandService productCommandService;
    private final ProductRepository productRepository;

    public ProductsContextFacadeImpl(ProductQueryService productQueryService, ProductCommandService productCommandService, ProductRepository productRepository) {
        this.productQueryService = productQueryService;
        this.productCommandService = productCommandService;
        this.productRepository = productRepository;
    }

    @Override
    public String getProductNameById(Long productId) {
        var product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        return product.getName();
    }

    @Override
    public String changeQuantityOfProduct(Long productId, int quantity, String action) {
        var command = new ChangeQuantityOfProductCommand("decrease", quantity, productId);
        var product = productCommandService.handle(command);
        if (product.isPresent()) {
            return "Product quantity updated successfully";
        } else {
            return "Product quantity update failed";
        }
    }
}
