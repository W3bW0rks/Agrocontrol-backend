package com.agrocontrol.backend.fields.interfaces.rest.resources;

public record CreateWorkerResource(
        Long producerId,
        String fullName,
        String documentNumber
) {
}
