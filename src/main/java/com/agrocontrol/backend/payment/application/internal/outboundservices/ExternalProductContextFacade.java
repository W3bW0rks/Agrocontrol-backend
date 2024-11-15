package com.agrocontrol.backend.payment.application.internal.outboundservices;

import com.agrocontrol.backend.store.interfaces.acl.ProductContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ExternalProductContextFacade {
    private final ProductContextFacade productContextFacade;

    public ExternalProductContextFacade(ProductContextFacade productContextFacade) {
        this.productContextFacade = productContextFacade;
    }

    public Double getUnitPriceProductByProductId(Long productId) {
        double quantity = productContextFacade.getUnitPriceByProductId(productId);
        if (quantity == 0) {
            throw new IllegalStateException("Failed to get quantity for product with ID %s.".formatted(productId));
        }
        return quantity;
    }

    public String getProductNameByProductId(Long productId) {
        String name = productContextFacade.getProductNameByProductId(productId);
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Product with ID %s not found or has no assigned name.".formatted(productId));
        }
        return name;
    }

    public Long getUserIdByProductId(Long productId) {
        Long userId = productContextFacade.getUserIdByProductId(productId);
        if (userId == 0L) {
            throw new IllegalArgumentException("User ID not found for product with ID %s.".formatted(productId));
        }
        return userId;
    }

    public void validateProductId(Long productId) {
        boolean isValid = productContextFacade.ValidateProductId(productId);
        if (!isValid) {
            throw new IllegalArgumentException("Product with ID %s does not exist.".formatted(productId));
        }
    }

    public void reduceProductQuantity(Long productId, Integer quantityToReduce) {
        boolean result = productContextFacade.reduceProductQuantity(productId, quantityToReduce);
        if (!result) {
            throw new IllegalStateException("Failed to reduce quantity for product with ID %s.".formatted(productId));
        }
    }
}
