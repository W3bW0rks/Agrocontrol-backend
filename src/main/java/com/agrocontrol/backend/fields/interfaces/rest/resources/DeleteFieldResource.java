package com.agrocontrol.backend.fields.interfaces.rest.resources;

/**
 *
 * @param fieldId
 * @param producerId
 */
public record DeleteFieldResource(
        Long fieldId,
        Long producerId
) {
}
