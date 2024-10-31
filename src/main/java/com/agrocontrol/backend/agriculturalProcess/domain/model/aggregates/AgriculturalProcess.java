package com.agrocontrol.backend.agriculturalProcess.domain.model.aggregates;

import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.*;
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
    private boolean finished;

    @Embedded
    private AgriculturalActivityManager activityManager;

    protected AgriculturalProcess() {}

    public AgriculturalProcess(CreateAgriculturalProcessCommand command) {
        this.fieldId = command.fieldId();
        this.startDate = LocalDate.now();
        this.endDate = null;
        this.finished = false;
        this.activityManager = new AgriculturalActivityManager();
    }

    public void finish() {
        this.endDate = LocalDate.now();
        this.finished = true;
    }

    public void addActivity(AddSeedingToProcessCommand command) {
        activityManager.addActivity(this, ActivityType.SEEDING, command);
    }

    public void addActivity(AddIrrigationToProcessCommand command) {
        activityManager.addActivity(this, ActivityType.IRRIGATION, command);
    }

    public void addActivity(AddCropTreatmentToProcessCommand command) {
        activityManager.addActivity(this, ActivityType.CROP_TREATMENT, command);
    }

    public void addActivity(AddHarvestToProcessCommand command) {
        activityManager.addActivity(this, ActivityType.HARVEST, command);
    }

    public void addResourceToActivity(AddResourceToActivityCommand command, String name) {
        activityManager.addResourceToActivity(command, name);
    }

    public void applyActivityAction(ExecuteAgriculturalActivityActionCommand command) {
        switch (command.action()) {
            case "start":
                activityManager.startActivity(command.activityId());
                break;
            case "finish":
                activityManager.finishActivity(command.activityId());
                break;
            case "cancel":
                activityManager.cancelActivity(command.activityId());
                break;
        }
    }

    public List<AgriculturalActivity> getActivities() {
        return activityManager.getActivities();
    }

    public List<AgriculturalActivity> getActivitiesByType(ActivityType activityType) {
        return activityManager.getActivitiesByType(activityType);
    }

    public AgriculturalActivity getLastActivityByType(ActivityType activityType) {
        return activityManager.getLastActivityByType(activityType);
    }

    public AgriculturalActivity getActivityById(Long activityId) {
        return activityManager.getActivityById(activityId);
    }

    public AgriculturalActivity getLastActivityId() {
        return activityManager.getLatestActivityId();
    }
}
