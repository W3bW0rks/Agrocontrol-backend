package com.agrocontrol.backend.finances.domain.model.aggregates;

import com.agrocontrol.backend.finances.domain.model.commands.CreateFinanceCommand;
import com.agrocontrol.backend.finances.domain.model.valueobjects.UserId;
import com.agrocontrol.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Entity
public class Finance extends AuditableAbstractAggregateRoot<Finance> {

    @Embedded
    private UserId userId;

    @NotNull
    private LocalDate date;

    @NotNull
    private String type;

    @NotNull
    private double value;

    protected Finance() {}

    public Finance(CreateFinanceCommand command) {
        this.userId = new UserId(command.userId());
        this.date = LocalDate.now();
        this.type = command.type();
        this.value = command.value();
    }

    public Long getUserId() {
        return userId.userId();
    }
}
