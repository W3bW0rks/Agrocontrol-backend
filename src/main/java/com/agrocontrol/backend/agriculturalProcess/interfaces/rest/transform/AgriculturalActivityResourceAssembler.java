package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.transform;

import com.agrocontrol.backend.agriculturalProcess.domain.model.entities.CropTreatment;
import com.agrocontrol.backend.agriculturalProcess.domain.model.entities.Harvest;
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
                    entity.getWorkersTotalCost(),
                    irrigation.getHoursIrrigated(),
                    null,
                    null,
                    null,
                    0,
                    0,
                    0,
                    resources
            );
            case Seeding seeding -> new AgriculturalActivityResource(
                    entity.getId(),
                    entity.getAgriculturalProcess().getId(),
                    ActivityType.SEEDING.name(),
                    entity.getDate(),
                    entity.getActivityStatus().name(),
                    entity.getWorkersTotalCost(),
                    null,
                    seeding.getPlantType(),
                    seeding.getQuantityPlanted(),
                    null,
                    0,
                    0,
                    0,
                    resources
            );
            case CropTreatment cropTreatment -> new AgriculturalActivityResource(
                    entity.getId(),
                    entity.getAgriculturalProcess().getId(),
                    ActivityType.CROP_TREATMENT.name(),
                    entity.getDate(),
                    entity.getActivityStatus().name(),
                    entity.getWorkersTotalCost(),
                    null,
                    null,
                    null,
                    cropTreatment.getTreatmentType(),
                    0,
                    0,
                    0,
                    resources
            );
            case Harvest harvest -> new AgriculturalActivityResource(
                    entity.getId(),
                    entity.getAgriculturalProcess().getId(),
                    ActivityType.HARVEST.name(),
                    entity.getDate(),
                    entity.getActivityStatus().name(),
                    entity.getWorkersTotalCost(),
                    null,
                    null,
                    null,
                    null,
                    harvest.getQuantityInKg(),
                    harvest.getPricePerKg(),
                    harvest.getTotalIncome(),
                    resources
            );
            default -> null;
        };
    }
}

