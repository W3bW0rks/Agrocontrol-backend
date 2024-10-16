package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.transform;

import com.agrocontrol.backend.agriculturalProcess.domain.model.aggregates.AgriculturalProcess;
import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.AgriculturalActivity;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources.AgriculturalActivityResource;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources.AgriculturalProcessResource;

import java.util.List;

public class AgriculturalProcessResourceFromEntityAssembler {
    public static AgriculturalProcessResource toResourceFromEntity(AgriculturalProcess entity) {
        List<AgriculturalActivityResource> activityResources = entity.getActivities().stream()
                .map(AgriculturalActivityResourceAssembler::toResourceFromEntity)
                .toList();

        return new AgriculturalProcessResource(
                entity.getId(),
                entity.getFieldId(),
                entity.getStartDate(),
                entity.getEndDate(),
                entity.isFinished(),
                activityResources
        );
    }
}
