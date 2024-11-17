package com.agrocontrol.backend.fields.domain.model.commands;

import com.agrocontrol.backend.fields.domain.exceptions.FieldIdNotValidException;

public record DeleteFieldCommand(
        Long fieldId,
        Long producerId
) {
    /**
     * Constructor
     * @param fieldId the field ID
     *                cannot be null or less than zero
     * @param producerId the producer ID
     *                   cannot be null or less than zero
     */
    public DeleteFieldCommand{
        if(fieldId == null || fieldId <= 0)
            throw new FieldIdNotValidException(fieldId);
    }
}
