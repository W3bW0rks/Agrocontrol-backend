package com.agrocontrol.backend.agriculturalProcess.domain.model.entities;

import com.agrocontrol.backend.agriculturalProcess.domain.model.aggregates.AgriculturalProcess;
import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.ActivityType;
import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.AgriculturalActivity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue("CROP_TREATMENT")
public class CropTreatment extends AgriculturalActivity {

    @Getter
    private String treatmentType;

    protected CropTreatment() {}

    public CropTreatment(AgriculturalProcess agriculturalProcess, ActivityType activityType, String date, String treatmentType) {
        super(agriculturalProcess, activityType, date);
        this.treatmentType = treatmentType;
    }
}
