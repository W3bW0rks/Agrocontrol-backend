package com.agrocontrol.backend.agriculturalProcess.interfaces.rest.transform;

import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddCropTreatmentToProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources.AddCropTreatmentToProcessResource;

public class AddCropTreatmentToProcessCommandFromResourceAssembler {
    public static AddCropTreatmentToProcessCommand toCommandFromResource(AddCropTreatmentToProcessResource resource) {
        return new AddCropTreatmentToProcessCommand(
                resource.date(),
                resource.treatmentType(),
                resource.agriculturalProcessId()
        );
    }
}
