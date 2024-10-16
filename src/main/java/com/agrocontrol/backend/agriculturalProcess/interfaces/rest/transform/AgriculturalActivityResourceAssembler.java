package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.transform;

import com.agrocontrol.backend.agriculturalProcess.domain.model.entities.Irrigation;
import com.agrocontrol.backend.agriculturalProcess.domain.model.entities.Seeding;
import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.ActivityType;
import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.AgriculturalActivity;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources.AgriculturalActivityResource;

public class AgriculturalActivityResourceAssembler {
    public static AgriculturalActivityResource toResourceFromEntity(AgriculturalActivity entity) {
        if (entity instanceof Irrigation irrigation) {
            return new AgriculturalActivityResource(
                    entity.getId(),
                    entity.getAgriculturalProcess().getId(),
                    ActivityType.IRRIGATION.name(),
                    entity.getDate(),
                    irrigation.getHoursIrrigated(),
                    null,
                    null
            );
        } else if (entity instanceof Seeding seeding) {
            return new AgriculturalActivityResource(
                    entity.getId(),
                    entity.getAgriculturalProcess().getId(),
                    ActivityType.SEEDING.name(),
                    entity.getDate(),
                    null,
                    seeding.getPlantType(),
                    seeding.getQuantityPlanted()
            );
        } else {
            return null;
        }
    }
}

