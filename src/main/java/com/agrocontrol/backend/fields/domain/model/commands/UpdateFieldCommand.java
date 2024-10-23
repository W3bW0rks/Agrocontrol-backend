package com.agrocontrol.backend.fields.domain.model.commands;

/**
 * Command to update field
 * @param name The name of the field
 * @param location the location
 * @param size the size
 */
public record UpdateFieldCommand(
        String name,
        String location,
        Integer size
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
            throw new IllegalArgumentException("Field name cannot be null or empty");
        if(location == null || location.isBlank())
            throw new IllegalArgumentException("Location cannot be null or empty");
        if(size == null || size <= 0)
            throw new IllegalArgumentException("Size must be greater than 0");
    }
}
