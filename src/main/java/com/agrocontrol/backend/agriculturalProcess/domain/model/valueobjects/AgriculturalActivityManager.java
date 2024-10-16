package com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects;

import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddIrrigationToProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddSeedingToProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.domain.model.entities.Irrigation;
import com.agrocontrol.backend.agriculturalProcess.domain.model.entities.Seeding;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class AgriculturalActivityManager {

    @OneToMany(mappedBy = "agriculturalProcessId", cascade = CascadeType.ALL)
    private List<AgriculturalActivity> activities;

    public AgriculturalActivityManager() {
        this.activities = new ArrayList<>();
    }

    public void addActivity(Long agriculturalProcessId, ActivityType activityType, AddIrrigationToProcessCommand command) {
        Irrigation irrigation = new Irrigation(agriculturalProcessId, activityType, command.hoursIrrigated());
        this.activities.add(irrigation);
    }

    public void addActivity(Long agriculturalProcessId, ActivityType activityType, AddSeedingToProcessCommand command) {
        Seeding seeding = new Seeding(agriculturalProcessId, activityType, command.plantType(), command.quantityPlanted());
        this.activities.add(seeding);
    }
}
