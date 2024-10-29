package com.agrocontrol.backend.profiles.domain.model.aggregates;


import com.agrocontrol.backend.profiles.domain.model.commands.CreateDistributorCommand;
import com.agrocontrol.backend.profiles.domain.model.valueobjects.Phone;
import com.agrocontrol.backend.profiles.domain.model.valueobjects.Ruc;
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
public class Distributor extends AuditableAbstractAggregateRoot<Distributor> {
    @NotBlank
    private String fullName;

    @NotBlank
    private String city;

    @NotBlank
    private String country;

    @Embedded
    private Phone phone;

    @NotBlank
    private String companyName;

    @Embedded
    private Ruc ruc;

    @NotNull(message = "UserId cannot be null")
    @Positive(message = "UserId must be positive")
    private Long userId;

    public Distributor(CreateDistributorCommand command, Long userId) {
        this.fullName = command.fullName();
        this.city = command.city();
        this.country = command.country();
        this.phone = new Phone(command.phone());;
        this.companyName = command.companyName();
        this.ruc = new Ruc(command.ruc());
        this.userId = userId;
    }

    public Distributor() {}

    public String getPhone(){
        return phone.phone();
    }

    public String getRuc(){
        return ruc.ruc();
    }
}
