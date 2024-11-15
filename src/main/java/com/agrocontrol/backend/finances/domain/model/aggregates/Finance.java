package com.agrocontrol.backend.finances.domain.model.aggregates;

import com.agrocontrol.backend.finances.domain.model.commands.CreateFinanceCommand;
import com.agrocontrol.backend.finances.domain.model.valueobjects.AgriculturalProcessId;
import com.agrocontrol.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class Finance extends AuditableAbstractAggregateRoot<Finance> {

    @Embedded
    private AgriculturalProcessId agriculturalProcessId;

    @NotNull
    private LocalDate date;

    @NotNull
    private String type;

    @NotBlank
    private String description;

    @NotNull
    private double value;

    protected Finance() {}

    public Finance(CreateFinanceCommand command) {
        this.agriculturalProcessId = new AgriculturalProcessId(command.agriculturalProcessId());
        this.date = LocalDate.now();
        this.type = command.type();
        this.description = command.description();
        this.value = command.value();
    }

    public Long getAgriculturalProcessId() {
        return agriculturalProcessId.agriculturalProcessId();
    }
}
