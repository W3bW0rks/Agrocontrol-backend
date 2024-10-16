package com.agrocontrol.backend.agriculturalProcess.domain.model.aggregates;

import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddIrrigationToProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddSeedingToProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.CreateAgriculturalProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.ActivityType;
import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.AgriculturalActivity;
import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.AgriculturalActivityManager;
import com.agrocontrol.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Entity
public class AgriculturalProcess extends AuditableAbstractAggregateRoot<AgriculturalProcess> {

    @Getter
    @NotNull
    private Long fieldId;

    @Getter
    private LocalDate startDate;

    @Getter
    private LocalDate endDate;

    @Getter
    private boolean isFinished;

    @Embedded
    private AgriculturalActivityManager activityManager;

    protected AgriculturalProcess() {}

    public AgriculturalProcess(CreateAgriculturalProcessCommand command) {
        this.fieldId = command.fieldId();
        this.startDate = LocalDate.now();
        this.endDate = null;
        this.isFinished = false;
        this.activityManager = new AgriculturalActivityManager();
    }

    public void finish() {
        this.endDate = LocalDate.now();
        this.isFinished = true;
    }

    public void addActivity(AddSeedingToProcessCommand command) {
        activityManager.addActivity(this, ActivityType.SEEDING, command);
    }

    public void addActivity(AddIrrigationToProcessCommand command) {
        activityManager.addActivity(this, ActivityType.IRRIGATION, command);
    }

    public List<AgriculturalActivity> getActivities() {
        return activityManager.getActivities();
    }
}
