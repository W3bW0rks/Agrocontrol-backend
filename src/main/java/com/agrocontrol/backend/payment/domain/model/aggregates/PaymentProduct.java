package com.agrocontrol.backend.payment.domain.model.aggregates;

import com.agrocontrol.backend.payment.domain.model.commands.CreatePaymentProductCommand;
import com.agrocontrol.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull
    private Integer quantityProduct;

    @NotNull
    private Long userId;

    @NotNull
    private Long ownerProductId;

    @NotNull
    private Double totalCost;

    protected PaymentProduct() {}

    public PaymentProduct(CreatePaymentProductCommand command, Long ownerProductId, Double totalCost) {
        this.date = LocalDate.now();
        this.productId = command.productId();
        this.quantityProduct = command.quantityProduct();
        this.userId = command.userId();
        this.ownerProductId = ownerProductId;
        this.totalCost = totalCost;
    }
}
