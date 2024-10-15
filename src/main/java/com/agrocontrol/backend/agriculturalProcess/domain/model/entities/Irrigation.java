package com.agrocontrol.backend.agriculturalProcess.domain.model.entities;

import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddAgriculturalActivityCommand;
import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.AgriculturalActivity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;

@Entity
@DiscriminatorValue("IRRIGATION")
public class Irrigation extends AgriculturalActivity {

    @Getter
    private Integer hoursIrrigated;

    protected Irrigation() {}

    public Irrigation(AddAgriculturalActivityCommand command) {
        super(command.agriculturalProcessId(), command.activityType());
        this.hoursIrrigated = command.hoursIrrigated();
    }
}
