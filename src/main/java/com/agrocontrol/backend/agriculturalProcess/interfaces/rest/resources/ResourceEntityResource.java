package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources;

public record ResourceEntityResource(
        Long resourceId,
        String name,
        Integer cost,
        Integer quantity
) {
}
