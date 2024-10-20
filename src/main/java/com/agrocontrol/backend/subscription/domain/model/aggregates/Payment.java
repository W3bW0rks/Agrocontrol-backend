package com.agrocontrol.backend.subscription.domain.model.aggregates;


import com.agrocontrol.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.agrocontrol.backend.subscription.domain.model.commands.CreatePaymentCommand;
import com.agrocontrol.backend.subscription.domain.model.commands.RenewPaymentCommand;
import com.agrocontrol.backend.subscription.domain.model.commands.UpdatePlanTypeCommand;
import com.agrocontrol.backend.subscription.domain.model.valueobjects.PlanTypes;
import com.agrocontrol.backend.subscription.domain.model.valueobjects.SubscriptionId;
import com.agrocontrol.backend.subscription.domain.model.valueobjects.UserId;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDate;


@Getter
@Entity
public class Payment extends AuditableAbstractAggregateRoot<Payment> {

    private PlanTypes planType;

    @Embedded
    private UserId userId;

    @Embedded
    private SubscriptionId subscriptionId;

    private LocalDate startDate;

    private LocalDate renewalDate;

    private String status;

    private double cost;

    protected Payment() {}

    public Payment(CreatePaymentCommand command) {
        this.planType = command.planType();
        this.userId = new UserId(command.userId());
        this.subscriptionId = new SubscriptionId(command.subscriptionId());
        this.startDate = command.startDate();
        this.renewalDate = command.renewalDate();
        this.status = command.status();
        this.cost = command.cost();
    }

    public void updatePlanType(UpdatePlanTypeCommand command) {
        this.planType = command.planType();
    }

    public void renewPlan(RenewPaymentCommand command) {
        this.renewalDate = command.renewalDate();
    }

    public Long getUserId() {
        return this.userId.userId();
    }

    public Long getSubscriptionId() {
        return this.subscriptionId.subscriptionId();
    }
}
