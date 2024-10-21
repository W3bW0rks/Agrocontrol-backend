package com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects;

import com.agrocontrol.backend.agriculturalProcess.domain.model.aggregates.AgriculturalProcess;
import com.agrocontrol.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

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

    private double workersTotalCost;

    protected AgriculturalActivity() {}

    public AgriculturalActivity(AgriculturalProcess agriculturalProcess, ActivityType activityType, String date) {
        this.agriculturalProcess = agriculturalProcess;
        this.activityType = activityType;
        this.date = date;
    }
}
