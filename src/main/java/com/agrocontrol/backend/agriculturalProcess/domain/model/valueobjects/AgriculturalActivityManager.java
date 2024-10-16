package com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects;

import com.agrocontrol.backend.agriculturalProcess.domain.model.aggregates.AgriculturalProcess;
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

    @OneToMany(mappedBy = "agriculturalProcess", cascade = CascadeType.ALL)
    private List<AgriculturalActivity> activities;

    public AgriculturalActivityManager() {
        this.activities = new ArrayList<>();
    }

    public void addActivity(AgriculturalProcess agriculturalProcess, ActivityType activityType, AddIrrigationToProcessCommand command) {
        if (agriculturalProcess.isFinished()) {
            throw new IllegalArgumentException("Cannot add activity to a finished process");
        }
        Irrigation irrigation = new Irrigation(agriculturalProcess, activityType, command.hoursIrrigated());
        this.activities.add(irrigation);
    }

    public void addActivity(AgriculturalProcess agriculturalProcess, ActivityType activityType, AddSeedingToProcessCommand command) {
        if (agriculturalProcess.isFinished()) {
            throw new IllegalArgumentException("Cannot add activity to a finished process");
        }
        Seeding seeding = new Seeding(agriculturalProcess, activityType, command.plantType(), command.quantityPlanted());
        this.activities.add(seeding);
    }

    public List<AgriculturalActivity> getActivities() {
        return activities;
    }
}
