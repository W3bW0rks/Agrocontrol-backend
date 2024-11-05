package com.agrocontrol.backend.fields.interfaces.rest.resources;

public record WorkerResource(
        Long id,
        Long producerId,
        String fullName,
        String documentNumber
) {
}
