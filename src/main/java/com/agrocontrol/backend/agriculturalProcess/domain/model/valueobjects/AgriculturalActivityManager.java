package com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects;

import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddAgriculturalActivityCommand;
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

    public void addIrrigation(AddAgriculturalActivityCommand command) {
        Irrigation irrigation = new Irrigation(command);
        this.activities.add(irrigation);
    }

    public void addSeeding(AddAgriculturalActivityCommand command) {
        Seeding seeding = new Seeding(command);
        this.activities.add(seeding);
    }
}
