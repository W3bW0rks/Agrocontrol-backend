package com.agrocontrol.backend.fields.interfaces.rest.resources;

import com.agrocontrol.backend.fields.domain.model.valueobjects.ProducerId;
public record FieldResource(
        Long id,
        Long producerId,
        String fieldName,
        String location,
        Integer size

) {
}
