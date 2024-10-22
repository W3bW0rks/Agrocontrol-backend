package com.agrocontrol.backend.profiles.domain.model.aggregates;



import com.agrocontrol.backend.profiles.domain.model.commands.CreateAgriculturalProducerCommand;
import com.agrocontrol.backend.profiles.domain.model.valueobjects.Dni;
import com.agrocontrol.backend.profiles.domain.model.valueobjects.Phone;
import com.agrocontrol.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class AgriculturalProducer extends AuditableAbstractAggregateRoot<AgriculturalProducer> {
    @NotBlank
    private String fullName;

    @NotBlank
    private String city;

    @NotBlank
    private String country;

    @Embedded
    private Phone phone;

    @Embedded
    private Dni dni;

    @NotNull(message = "UserId cannot be null")
    @Positive(message = "UserId must be positive")
    private Long userId;



    public AgriculturalProducer(CreateAgriculturalProducerCommand command, Long userId) {
        this.fullName = command.fullName();
        this.city = command.city();
        this.country = command.country();
        this.phone = new Phone(command.phone());
        this.dni = new Dni(command.dni());
        this.userId = userId;
    }

    public AgriculturalProducer() {}

    public String getDni() {
        return dni.dni();
    }

    public String getPhone(){
        return phone.phone();
    }

}
