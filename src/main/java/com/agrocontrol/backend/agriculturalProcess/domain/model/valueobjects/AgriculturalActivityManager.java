package com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects;

import com.agrocontrol.backend.agriculturalProcess.domain.model.aggregates.AgriculturalProcess;
import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.*;
import com.agrocontrol.backend.agriculturalProcess.domain.model.entities.CropTreatment;
import com.agrocontrol.backend.agriculturalProcess.domain.model.entities.Harvest;
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

    // Constructor initializes the activities list
    public AgriculturalActivityManager() {
        this.activities = new ArrayList<>();
    }

    // Adds an irrigation activity to the list
    public void addActivity(AgriculturalProcess agriculturalProcess, ActivityType activityType, AddIrrigationToProcessCommand command) {
        Irrigation irrigation = new Irrigation(agriculturalProcess, activityType, command.date(), command.hoursIrrigated());
        this.activities.add(irrigation);
    }

    // Adds a seeding activity to the list, but only if it doesn't already exist
    public void addActivity(AgriculturalProcess agriculturalProcess, ActivityType activityType, AddSeedingToProcessCommand command) {
        boolean seedingExists = activities.stream()
                .anyMatch(activity -> activity instanceof Seeding);
        if (seedingExists) {
            throw new IllegalArgumentException("Seeding activity already exists");
        }
        Seeding seeding = new Seeding(agriculturalProcess, activityType, command.date(), command.plantType(), command.quantityPlanted());
        this.activities.add(seeding);
    }

    // Adds a crop treatment activity to the list
    public void addActivity(AgriculturalProcess agriculturalProcess, ActivityType activityType, AddCropTreatmentToProcessCommand command) {
        CropTreatment cropTreatment = new CropTreatment(agriculturalProcess, activityType, command.date(), command.treatmentType());
        this.activities.add(cropTreatment);
    }

    public void addActivity(AgriculturalProcess agriculturalProcess, ActivityType activityType, AddHarvestToProcessCommand command) {
        Harvest harvest = new Harvest(agriculturalProcess, activityType, command.date(), command.quantityInKg(), command.pricePerKg());
        this.activities.add(harvest);
    }

    public void addResourceToActivity(AddResourceToActivityCommand command, String name) {
        AgriculturalActivity activity = getActivityById(command.activityId());
        activity.addResource(command.resourceId(), command.description(), name, command.cost(), command.quantity());
    }

    // Check if an activity is in a specific status
    private boolean checkActivityStatus(Long activityId, ActivityStatus expectedStatus) {
        var activity = getActivityById(activityId);
        if (!activity.getActivityStatus().equals(expectedStatus)) {
            throw new IllegalArgumentException("Activity is not in the expected state: " + expectedStatus);
        }
        return true;
    }

    // Start an activity if it's neither finished nor cancelled
    public void startActivity(Long activityId) {
        checkActivityStatus(activityId, ActivityStatus.NOT_STARTED); // Activity must not be started yet
        getActivityById(activityId).start();
    }

    // Finish an activity if it's in progress
    public void finishActivity(Long activityId) {
        checkActivityStatus(activityId, ActivityStatus.IN_PROGRESS); // Activity must be in progress
        getActivityById(activityId).finish();
    }

    // Cancel an activity if it's not finished
    public void cancelActivity(Long activityId) {
        // Verifica que la actividad no esté en estado 'FINISHED' antes de cancelarla
        if (checkActivityStatus(activityId, ActivityStatus.IN_PROGRESS) || checkActivityStatus(activityId, ActivityStatus.NOT_STARTED)) {
            getActivityById(activityId).cancel();
        } else {
            throw new IllegalArgumentException("Cannot cancel the activity as it is already finished.");
        }
    }


    // Returns the list of activities
    public List<AgriculturalActivity> getActivities() {
        return activities;
    }

    // Returns activities filtered by type
    public List<AgriculturalActivity> getActivitiesByType(ActivityType activityType) {
        List<AgriculturalActivity> activitiesByType = new ArrayList<>();
        for (AgriculturalActivity activity : activities) {
            if (activity.getActivityType().equals(activityType)) {
                activitiesByType.add(activity);
            }
        }
        return activitiesByType;
    }

    // Returns the last activity of a specific type
    public AgriculturalActivity getLastActivityByType(ActivityType activityType) {
        for (int i = activities.size() - 1; i >= 0; i--) {
            AgriculturalActivity activity = activities.get(i);
            if (activity.getActivityType().equals(activityType)) {
                return activity;
            }
        }
        return null;
    }

    // Returns an activity by its ID
    public AgriculturalActivity getActivityById(Long activityId) {
        return activities.stream()
                .filter(a -> a.getId().equals(activityId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Activity not found"));
    }

    public AgriculturalActivity getLatestActivityId() {
        return activities.getLast();
    }
}