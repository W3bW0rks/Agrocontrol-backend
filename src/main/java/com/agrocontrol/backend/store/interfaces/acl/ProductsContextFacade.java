package com.agrocontrol.backend.store.interfaces.acl;

public interface ProductsContextFacade {

    String getProductNameById(Long productId);

    String changeQuantityOfProduct(Long productId, int quantity, String action);
}
