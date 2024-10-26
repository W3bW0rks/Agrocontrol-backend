package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.transform;

import com.agrocontrol.backend.agriculturalProcess.domain.model.entities.CropTreatment;
import com.agrocontrol.backend.agriculturalProcess.domain.model.entities.Irrigation;
import com.agrocontrol.backend.agriculturalProcess.domain.model.entities.Seeding;
import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.ActivityType;
import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.AgriculturalActivity;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources.AgriculturalActivityResource;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources.ResourceEntityResource;

import java.util.List;

public class AgriculturalActivityResourceAssembler {
    public static AgriculturalActivityResource toResourceFromEntity(AgriculturalActivity entity) {
        List<ResourceEntityResource> resources = entity.getResources().stream()
                .map(ResourceEntityResourceAssembler::toResourceFromEntity).toList();

        return switch (entity) {
            case Irrigation irrigation -> new AgriculturalActivityResource(
                    entity.getId(),
                    entity.getAgriculturalProcess().getId(),
                    ActivityType.IRRIGATION.name(),
                    entity.getDate(),
                    entity.getActivityStatus().name(),
                    irrigation.getHoursIrrigated(),
                    null,
                    null,
                    null,
                    resources
            );
            case Seeding seeding -> new AgriculturalActivityResource(
                    entity.getId(),
                    entity.getAgriculturalProcess().getId(),
                    ActivityType.SEEDING.name(),
                    entity.getDate(),
                    entity.getActivityStatus().name(),
                    null,
                    seeding.getPlantType(),
                    seeding.getQuantityPlanted(),
                    null,
                    resources
            );
            case CropTreatment cropTreatment -> new AgriculturalActivityResource(
                    entity.getId(),
                    entity.getAgriculturalProcess().getId(),
                    ActivityType.CROP_TREATMENT.name(),
                    entity.getDate(),
                    entity.getActivityStatus().name(),
                    null,
                    null,
                    null,
                    cropTreatment.getTreatmentType(),
                    resources
            );
            default -> null;
        };
    }
}

