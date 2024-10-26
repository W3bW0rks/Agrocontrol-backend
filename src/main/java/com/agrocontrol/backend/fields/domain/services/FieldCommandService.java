package com.agrocontrol.backend.fields.domain.services;

import com.agrocontrol.backend.fields.domain.model.aggregates.Field;
import com.agrocontrol.backend.fields.domain.model.commands.CreateFieldCommand;
import com.agrocontrol.backend.fields.domain.model.commands.DeleteFieldCommand;
import com.agrocontrol.backend.fields.domain.model.commands.UpdateFieldCommand;

import java.util.Optional;

/**
 * This interface represents the service that handles the commands related to the Field aggregate
 */
public interface FieldCommandService {
    /**
     * Handles the command to create a new field
     * @param command The command to create a new field containing the field data
     * @return The created field ID
     */
    Optional<Field> handle(CreateFieldCommand command);

    /**
     * Handles the command to update an existing field containing the field data
     * @param command The command to update an existing field
     * @return The updated field
     */
    Optional<Field> handle(UpdateFieldCommand command);

    /**
     *  Handles the command to delete an existing field
     * @param command The command to delete an existing field containing the field ID
     */
    void handle(DeleteFieldCommand command);
}
