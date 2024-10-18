package com.agrocontrol.backend.profiles.domain.model.aggregates;


import com.agrocontrol.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
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

    @NotBlank
    private String phone;

    @NotBlank
    private String companyName;

    @NotBlank
    private String ruc;

    @NotNull(message = "UserId cannot be null")
    @Positive(message = "UserId must be positive")
    private Long userId;

    public Distributor(String fullName, String city, String country, String phone, String companyName, String ruc, Long userId) {
        this.fullName = fullName;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.companyName = companyName;
        this.ruc = ruc;
        this.userId = userId;
    }

    public Distributor() {}
}
