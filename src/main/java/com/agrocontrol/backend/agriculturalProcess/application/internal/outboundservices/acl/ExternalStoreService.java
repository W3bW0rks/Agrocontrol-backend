package com.agrocontrol.backend.agriculturalProcess.application.internal.outboundservices.acl;

import com.agrocontrol.backend.store.interfaces.acl.ProductsContextFacade;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("externalStoreService")
public class ExternalStoreService {
    private final ProductsContextFacade productsContextFacade;

    public ExternalStoreService(ProductsContextFacade productsContextFacade) {
        this.productsContextFacade = productsContextFacade;
    }

    public Optional<String> getProductNameById(Long productId) {
        return this.productsContextFacade.getProductNameById(productId);
    }
}
