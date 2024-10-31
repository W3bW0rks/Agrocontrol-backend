package com.agrocontrol.backend.fields.interfaces.rest.resources;

public record WorkerResource(
        Long producerId,
        String fullName,
        String documentNumber
) {
}
