package com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects;

import com.agrocontrol.backend.agriculturalProcess.domain.model.aggregates.AgriculturalProcess;
import com.agrocontrol.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type",
        discriminatorType = DiscriminatorType.STRING)
public class AgriculturalActivity extends AuditableAbstractAggregateRoot<AgriculturalActivity> {

    @ManyToOne
    @JoinColumn(name = "agricultural_process_id", nullable = false)
    @NotNull
    private AgriculturalProcess agriculturalProcess;

    @NotNull
    private ActivityType activityType;

    @NotNull
    private String date;

    @NotNull
    private ActivityStatus activityStatus;

    private double workersTotalCost;

    @ElementCollection
    @CollectionTable(name = "activity_resources", joinColumns = @JoinColumn(name = "activity_id"))
    private List<Resource> resources = new ArrayList<>();

    protected AgriculturalActivity() {}

    public AgriculturalActivity(AgriculturalProcess agriculturalProcess, ActivityType activityType, String date) {
        this.agriculturalProcess = agriculturalProcess;
        this.activityType = activityType;
        this.date = date;
        this.activityStatus = ActivityStatus.NOT_STARTED;
    }

    public void start() {
        this.activityStatus = ActivityStatus.IN_PROGRESS;
    }

    public void finish() {
        this.activityStatus = ActivityStatus.FINISHED;
    }

    public void cancel() {
        this.activityStatus = ActivityStatus.CANCELLED;
    }

    public void addResource(Long resourceId, String description, String name, Integer cost, Integer quantity) {
        this.resources.add(new Resource(resourceId, description, name, cost, quantity));
        calculateWorkersTotalCost();
    }

    private void calculateWorkersTotalCost() {
        this.workersTotalCost = this.resources.stream().mapToDouble(Resource::getCost).sum();
    }
}
