package com.agrocontrol.backend.store.interfaces.acl;

import java.util.Optional;

public interface ProductsContextFacade {

    String getProductNameById(Long productId);

    String changeQuantityOfProduct(Long productId, int quantity, String action);
}
