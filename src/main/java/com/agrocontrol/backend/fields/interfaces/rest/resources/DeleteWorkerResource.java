package com.agrocontrol.backend.fields.interfaces.rest.resources;

public record DeleteWorkerResource(
        Long workerId,
        Long producerId
) {
}
