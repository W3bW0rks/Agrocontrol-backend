package com.agrocontrol.backend.payment.domain.model.aggregates;

import com.agrocontrol.backend.payment.domain.model.commands.CreatePaymentCommand;
import com.agrocontrol.backend.payment.domain.model.valueobjects.SubscriptionId;
import com.agrocontrol.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Payment extends AuditableAbstractAggregateRoot<Payment> {

    private SubscriptionId subscriptionId;

    @Embedded

    private LocalDate date;
    private String state;
    private String cardHolderName;
    private String cardNumber;
    private String ExpireDate;
    private String cvv;
    protected Payment() {}

    public Payment(CreatePaymentCommand command){
        this.subscriptionId = command.subscriptionId();
        this.date = command.date();
        this.state = command.state();
        this.cardHolderName = command.cardHolderName();
        this.cardNumber = command.cardNumber();
        this.ExpireDate = command.ExpireDate();
        this.cvv = command.cvv();
    }




}
