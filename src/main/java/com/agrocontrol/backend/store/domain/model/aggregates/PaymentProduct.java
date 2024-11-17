package com.agrocontrol.backend.store.domain.model.aggregates;

import com.agrocontrol.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.agrocontrol.backend.store.domain.model.commands.CreatePaymentProductCommand;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class PaymentProduct extends AuditableAbstractAggregateRoot<PaymentProduct> {
    private LocalDate date;

    @NotNull
    private Long productId;

    @NotBlank
    private String productName;

    @NotNull
    private Integer quantityProduct;

    @NotNull
    private Long userId;

    @NotNull
    private Long ownerProductId;

    @NotNull
    private Double totalCost;

    protected PaymentProduct() {}

    public PaymentProduct(CreatePaymentProductCommand command, String productName, Long ownerProductId, Double totalCost) {
        this.date = LocalDate.now();
        this.productId = command.productId();
        this.productName = productName;
        this.quantityProduct = command.quantityProduct();
        this.userId = command.userId();
        this.ownerProductId = ownerProductId;
        this.totalCost = totalCost;
    }
}
