package com.agrocontrol.backend.store.interfaces.acl;

public interface ProductContextFacade {
    Integer getQuantityProductByProductId(Long productId);
    String getProductNameByProductId(Long productId);
    Long getUserIdByProductId(Long productId);
    boolean ValidateProductId(Long productId);
    boolean reduceProductQuantity(Long productId, Integer quantityToReduce);
}
