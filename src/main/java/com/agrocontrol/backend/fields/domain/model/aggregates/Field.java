package com.agrocontrol.backend.fields.domain.model.aggregates;

import com.agrocontrol.backend.fields.domain.model.commands.CreateFieldCommand;
import com.agrocontrol.backend.fields.domain.model.commands.UpdateFieldCommand;
import com.agrocontrol.backend.fields.domain.model.valueobjects.ProducerId;
import com.agrocontrol.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class Field extends AuditableAbstractAggregateRoot<Field> {
    @Embedded
    private ProducerId producerId;

    @NotNull
    private String fieldName;

    @NotNull
    private String location;

    @NotNull
    private Integer size;

    protected Field() {}

    public Field(CreateFieldCommand command) {
        this.producerId = new ProducerId(command.producerId());
        this.fieldName = command.fieldName();
        this.location = command.location();
        this.size = command.size();
    }

    public void updateField(UpdateFieldCommand command) {
        this.fieldName=command.name();
        this.location=command.location();
        this.size=command.size();
    }

    public Long getProducerId(){
        return this.producerId.producerId();
    }

}
