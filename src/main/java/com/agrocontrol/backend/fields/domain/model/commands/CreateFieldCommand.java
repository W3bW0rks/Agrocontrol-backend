package com.agrocontrol.backend.fields.domain.model.commands;

import com.agrocontrol.backend.fields.domain.exceptions.FieldLocationNotValidException;
import com.agrocontrol.backend.fields.domain.exceptions.FieldNameNotValidException;
import com.agrocontrol.backend.fields.domain.exceptions.FieldSizeNotValidException;

/**
 *  command to create a Field
 * @param producerId the producer ID
 * @param fieldName the field name
 * @param location the location
 * @param size the size
 */
public record CreateFieldCommand(
        Long producerId,
        String fieldName,
        String location,
        Integer size
) {
    /**
     * Constructor
     * @param producerId the producer ID
     *                   cannot be null or empty
     * @param fieldName the field name
     *                  cannot be null or empty
     * @param location the location
     *                 cannot be null or empty
     * @param size the size
     *             cannot be null or less than zero
     */
    public CreateFieldCommand {
        if(fieldName == null || fieldName.isBlank())
            throw new FieldNameNotValidException(fieldName);
        if(location== null || location.isBlank())
            throw new FieldLocationNotValidException(location);
        if(size == null || size <= 0)
            throw new FieldSizeNotValidException(size);
    }
}
