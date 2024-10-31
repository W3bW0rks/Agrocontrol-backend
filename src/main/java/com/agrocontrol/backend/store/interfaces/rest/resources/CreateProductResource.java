package com.agrocontrol.backend.store.interfaces.rest.resources;

public record CreateProductResource(Long userId, String name, String quantityPerUnit,
                                    double unitPrice, int quantity, String photoUrl) {
}
