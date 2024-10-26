package com.agrocontrol.backend.fields.domain.model.commands;

import com.agrocontrol.backend.fields.domain.exceptions.FieldLocationNotValidException;
import com.agrocontrol.backend.fields.domain.exceptions.FieldNameNotValidException;
import com.agrocontrol.backend.fields.domain.exceptions.FieldSizeNotValidException;

/**
 * Command to update field
 * @param name The name of the field
 * @param location the location
 * @param size the size
 */
public record UpdateFieldCommand(
        String name,
        String location,
        Integer size,
        Long FieldId,
        Long producerId
) {
    /**
     * Constructor
     * @param name the name field
     *             cannot be null or empty
     * @param location the location
     *                cannot be null or empty
     * @param size the size
     *             cannot be 0 or less than zero
     */
    public UpdateFieldCommand{
        if(name == null || name.isBlank())
            throw new FieldNameNotValidException(name);
        if(location == null || location.isBlank())
            throw new FieldLocationNotValidException(location);
        if(size == null || size <= 0)
            throw new FieldSizeNotValidException(size);
    }
}
