package com.agrocontrol.backend.finances.interfaces.rest.transform;

import com.agrocontrol.backend.finances.domain.model.commands.CreateFinanceCommand;
import com.agrocontrol.backend.finances.interfaces.rest.resources.CreateFinanceResource;

public class CreateFinanceCommandFromResourceAssembler {
    public static CreateFinanceCommand toCommandFromResource(CreateFinanceResource resource) {
        return new CreateFinanceCommand(
                resource.agriculturalProcessId(),
                resource.type(),
                resource.description(),
                resource.value()
        );
    }
}
