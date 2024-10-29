package com.agrocontrol.backend.store.interfaces.rest.resources;

public record ChangeQuantityOfProductResource(
        String action,
        Integer quantity
) {
}
