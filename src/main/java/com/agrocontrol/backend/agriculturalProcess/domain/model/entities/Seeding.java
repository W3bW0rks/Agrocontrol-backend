package com.agrocontrol.backend.agriculturalProcess.domain.model.entities;

import com.agrocontrol.backend.agriculturalProcess.domain.model.aggregates.AgriculturalProcess;
import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.ActivityType;
import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.AgriculturalActivity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Entity
@DiscriminatorValue("SEEDING")
public class Seeding extends AgriculturalActivity {

    @Getter
    @NotNull
    private String plantType;

    @Getter
    @NotNull
    private Integer quantityPlanted;

    protected Seeding() {}

    public Seeding(AgriculturalProcess agriculturalProcess, ActivityType activityType, String plantType, Integer quantityPlanted) {
        super(agriculturalProcess, activityType);
        this.plantType = plantType;
        this.quantityPlanted = quantityPlanted;
    }

}
