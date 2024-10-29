package com.agrocontrol.backend.store.domain.model.aggregates;

import com.agrocontrol.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.agrocontrol.backend.store.domain.model.commands.CreateProductCommand;
import com.agrocontrol.backend.store.domain.model.commands.DecreaseQuantityCommand;
import com.agrocontrol.backend.store.domain.model.commands.IncreaseQuantityCommand;
import com.agrocontrol.backend.store.domain.model.commands.UpdateProductOwnerCommand;
import com.agrocontrol.backend.store.domain.model.valueobjects.UserId;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class Product extends AuditableAbstractAggregateRoot<Product> {

    @Embedded
    private UserId userId;

    @NotNull
    private String name;
    @NotNull
    private String quantityPerUnit;
    @NotNull
    private double unitPrice;
    @NotNull
    private Integer quantity;

    protected Product() {}

    public Product(CreateProductCommand command) {
        this.userId = new UserId(command.userId());
        this.name = command.name();
        this.quantityPerUnit = command.quantityPerUnit();
        this.unitPrice = command.unitPrice();
        this.quantity = command.quantity();
    }

    public void updateProductOwner(UpdateProductOwnerCommand command) {
        this.userId = new UserId(command.userId());
    }

    public void increaseQuantity(IncreaseQuantityCommand command) {
        this.quantity += command.quantity();
    }

    public void decreaseQuantity(DecreaseQuantityCommand command) {

        if (this.quantity == 0)
            throw new IllegalArgumentException("Product is out of stock");

        if (this.quantity - command.quantity() <= 0) {

            var exceededQuantity = command.quantity() - this.quantity;
            this.quantity = 0;
            
            throw new IllegalArgumentException("Product is out of stock. Exceeded quantity: " + exceededQuantity);
        }

        this.quantity -= command.quantity();
    }

    public Long getUserId() {
        return userId.userId();
    }
}