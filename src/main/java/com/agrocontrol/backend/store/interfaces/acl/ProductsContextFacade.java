package com.agrocontrol.backend.store.interfaces.acl;

import java.util.Optional;

public interface ProductsContextFacade {

    Optional<String> getProductNameById(Long productId);
}
