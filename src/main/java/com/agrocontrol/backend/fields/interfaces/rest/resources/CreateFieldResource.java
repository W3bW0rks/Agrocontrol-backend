package com.agrocontrol.backend.fields.interfaces.rest.resources;

import com.agrocontrol.backend.fields.domain.model.valueobjects.ProducerId;

/**
 * Resource class for creating a new field
 */
public record CreateFieldResource(
        Long producerId,
        String fieldName,
        String location,
        Integer size
) {
}
