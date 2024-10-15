package com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects;

import com.agrocontrol.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type",
        discriminatorType = DiscriminatorType.STRING)
public class AgriculturalActivity extends AuditableAbstractAggregateRoot<AgriculturalActivity> {

    @NotNull
    private Long agriculturalProcessId;

    @NotNull
    private ActivityType activityType;

    @NotNull
    private LocalDate date;

    private double workersTotalCost;

    protected AgriculturalActivity() {}

    public AgriculturalActivity(Long agriculturalProcessId, ActivityType activityType) {
        this.agriculturalProcessId = agriculturalProcessId;
        this.activityType = activityType;
        this.date = LocalDate.now();
    }
}
