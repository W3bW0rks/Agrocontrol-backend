package com.agrocontrol.backend.profiles.domain.model.aggregates;



import com.agrocontrol.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String phone;

    @NotBlank
    private String dni;

    @NotBlank
    private Long userId;

    public AgriculturalProducer(String fullName, String city, String country, String phone, String dni, Long userId) {
        this.fullName = fullName;
        this.city = city;
        this.country = country;
        this.phone = phone;
        this.dni = dni;
        this.userId = userId;
    }

    public AgriculturalProducer() {}

}
