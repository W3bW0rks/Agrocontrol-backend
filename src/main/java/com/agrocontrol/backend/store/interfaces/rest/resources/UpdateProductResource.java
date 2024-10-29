package com.agrocontrol.backend.store.interfaces.rest.resources;

public record UpdateProductResource(
        String name,
        String quantityPerUnit,
        double unitPrice,
        Integer quantity,
        String photoUrl
) {
}
