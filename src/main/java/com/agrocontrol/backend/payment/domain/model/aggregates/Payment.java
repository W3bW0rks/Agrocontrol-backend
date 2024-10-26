package com.agrocontrol.backend.payment.domain.model.aggregates;

import com.agrocontrol.backend.payment.domain.model.commands.CreatePaymentCommand;
import com.agrocontrol.backend.payment.domain.model.valueobjects.SubscriptionId;
import com.agrocontrol.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Payment extends AuditableAbstractAggregateRoot<Payment> {

    @Embedded
    private SubscriptionId subscriptionId;


    private LocalDate date;

    @NotNull
    private String state;

    @NotNull
    private String cardHolderName;

    @NotNull
    @Size(max = 16)
    private String cardNumber;

    @NotNull
    private String ExpireDate;

    @NotNull
    private String cvv;

    protected Payment() {}

    public Payment(CreatePaymentCommand command){
        this.subscriptionId = new SubscriptionId(command.subscriptionId());
        this.date = command.date();
        this.state = command.state();
        this.cardHolderName = command.cardHolderName();
        this.cardNumber = command.cardNumber();
        this.ExpireDate = command.ExpireDate();
        this.cvv = command.cvv();
    }



    public Long getSubscriptionId() {
        return this.subscriptionId.subscriptionId();
    }



}
