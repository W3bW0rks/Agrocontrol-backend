package com.agrocontrol.backend.store.domain.model.aggregates;

import com.agrocontrol.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.agrocontrol.backend.store.domain.model.commands.ChangeQuantityOfProductCommand;
import com.agrocontrol.backend.store.domain.model.commands.CreateProductCommand;
import com.agrocontrol.backend.store.domain.model.commands.UpdateProductCommand;
import com.agrocontrol.backend.store.domain.model.commands.UpdateProductOwnerCommand;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Entity
public class Product extends AuditableAbstractAggregateRoot<Product> {

    @NotNull
    private Long userId;

    private Long previousId;

    @NotNull
    private String name;
    @NotNull
    private String quantityPerUnit;
    @NotNull
    private double unitPrice;
    @NotNull
    private Integer quantity;
    @NotNull
    private String photoUrl;

    protected Product() {}

    public Product(CreateProductCommand command) {
        this.userId = command.userId();
        this.previousId = 0L;
        this.name = command.name();
        this.quantityPerUnit = command.quantityPerUnit();
        this.unitPrice = command.unitPrice();
        this.quantity = command.quantity();
        this.photoUrl = command.photoUrl();
    }

    public Product(Long userId, Long previousId, String name, String quantityPerUnit, double unitPrice, Integer quantity, String photoUrl) {
        this.userId = userId;
        this.previousId = previousId;
        this.name = name;
        this.quantityPerUnit = quantityPerUnit;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
        this.photoUrl = photoUrl;
    }

    public void updateProduct(UpdateProductCommand command) {
        this.name = command.name();
        this.quantityPerUnit = command.quantityPerUnit();
        this.unitPrice = command.unitPrice();
        this.quantity = command.quantity();
        this.photoUrl = command.photoUrl();
    }

    public void updateProductOwner(UpdateProductOwnerCommand command) {
        this.userId = command.userId();
    }

    public void changeQuantity(ChangeQuantityOfProductCommand command) {
        if (command.action().contains("increase")) {
            increaseQuantity(command.quantity());
        } else {
            decreaseQuantity(command.quantity());
        }
    }

    public void increaseQuantity(Integer newQuantity) {
        this.quantity += newQuantity;
    }

    public void decreaseQuantity(Integer newQuantity) {
        if (this.quantity == 0)
            throw new IllegalArgumentException("Product is out of stock");

        if (this.quantity - newQuantity <= 0) {
            var exceededQuantity = newQuantity - this.quantity;
            this.quantity = 0;
            throw new IllegalArgumentException("Product is out of stock. Exceeded quantity: " + exceededQuantity);
        }

        this.quantity -= newQuantity;
    }
}
