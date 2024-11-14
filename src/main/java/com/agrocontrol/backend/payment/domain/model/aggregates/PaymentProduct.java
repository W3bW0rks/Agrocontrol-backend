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
    private String cardHolderName;

    @NotNull
    @Size(max = 16)
    private String cardNumber;

    @NotNull
    private String ExpireDate;

    @NotNull
    private String cvv;

    @NotNull
    private Long productId;

    @NotNull
    private Integer quantityProduct;

    @NotNull
    private Long userId;

    @NotNull
    private Long ownerProductId;

    protected PaymentProduct() {}

    public PaymentProduct(CreatePaymentProductCommand command, Long ownerProductId) {
        this.date = LocalDate.now();
        this.cardHolderName = command.cardHolderName();
        this.cardNumber = command.cardNumber();
        this.ExpireDate = command.ExpireDate();
        this.cvv = command.cvv();
        this.productId = command.productId();
        this.quantityProduct = command.quantityProduct();
        this.userId = command.userId();
        this.ownerProductId = ownerProductId;
    }
}
