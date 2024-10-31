package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.transform;

import com.agrocontrol.backend.agriculturalProcess.domain.model.aggregates.AgriculturalProcess;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources.AgriculturalProcessResource;

public class AgriculturalProcessResourceFromEntityAssembler {
    public static AgriculturalProcessResource toResourceFromEntity(AgriculturalProcess entity) {

        return new AgriculturalProcessResource(
                entity.getId(),
                entity.getFieldId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.isFinished()
        );
    }
}
