package com.agrocontrol.backend.finances.interfaces.rest.transform;

import com.agrocontrol.backend.finances.domain.model.aggregates.Finance;
import com.agrocontrol.backend.finances.interfaces.rest.resources.FinanceResource;

public class FinanceResourceFromEntityAssembler {
    public static FinanceResource toResourceFromEntity(Finance finance) {
        return new FinanceResource(
                finance.getId(),
                finance.getAgriculturalProcessId(),
                finance.getDate(),
                finance.getType(),
                finance.getDescription(),
                finance.getValue()
        );
    }
}
