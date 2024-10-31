package com.agrocontrol.backend.fields.domain.model.aggregates;

import com.agrocontrol.backend.fields.domain.model.commands.CreateWorkerCommand;
import com.agrocontrol.backend.fields.domain.model.valueobjects.ProducerId;
import com.agrocontrol.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class Worker extends AuditableAbstractAggregateRoot<Worker> {
    @Embedded
    ProducerId producerId;

    @NotNull
    String fullName;

    @NotNull
    String documentNumber;

    protected Worker() {}

    public Worker(CreateWorkerCommand command){
        this.producerId = new ProducerId(command.producerId());
        this.fullName = command.fullName();
        this.documentNumber = command.documentNumber();
    }

    public Long getProducerId() {
        return this.producerId.producerId();
    }


}
