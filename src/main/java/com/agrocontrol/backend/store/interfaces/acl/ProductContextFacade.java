package com.agrocontrol.backend.store.interfaces.acl;

public interface ProductContextFacade {
    Double getUnitPriceByProductId(Long productId);
    String getProductNameByProductId(Long productId);
    Long getUserIdByProductId(Long productId);
    boolean ValidateProductId(Long productId);
    boolean reduceProductQuantity(Long productId, Integer quantityToReduce);
}
