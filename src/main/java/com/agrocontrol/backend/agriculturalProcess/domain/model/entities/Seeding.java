package com.agrocontrol.backend.agriculturalProcess.domain.model.entities;

import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.ActivityType;
import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.AgriculturalActivity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("SEEDING")
public class Seeding extends AgriculturalActivity {

    @NotNull
    private String plantType;

    @NotNull
    private Integer quantityPlanted;

    protected Seeding() {}

    public Seeding(Long agriculturalProcessId, ActivityType activityType, String plantType, Integer quantityPlanted) {
        super(agriculturalProcessId, activityType);
        this.plantType = plantType;
        this.quantityPlanted = quantityPlanted;
    }

}
