package com.agrocontrol.backend.fields.interfaces.rest.resources;

public record UpdateFieldResource(
        String name,
        String location,
        Integer size,
        Long producerId
) {
}
