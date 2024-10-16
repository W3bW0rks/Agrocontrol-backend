package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.transform;

import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddSeedingToProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources.AddAgriculturalActivityResource;

public class AddAgriculturalActivityCommandFromResourceAssembler {
    public static AddSeedingToProcessCommand toCommandFromResource(AddAgriculturalActivityResource resource) {
        return new AddSeedingToProcessCommand(
                resource.agriculturalProcessId(),
                resource.activityType(),
                resource.hoursIrrigated(),
                resource.plantType(),
                resource.quantityPlanted(),
                resource.cropTreatmentType(),
                resource.totalQuantityInKg(),
                resource.pricePerKg(),
                resource.totalIncome()
        );
    }
}
