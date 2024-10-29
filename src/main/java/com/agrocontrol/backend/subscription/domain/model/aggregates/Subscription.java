package com.agrocontrol.backend.subscription.domain.model.aggregates;


import com.agrocontrol.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.agrocontrol.backend.subscription.domain.model.commands.CreateSubscriptionCommand;
import com.agrocontrol.backend.subscription.domain.model.commands.RenewSubscriptionCommand;
import com.agrocontrol.backend.subscription.domain.model.commands.UpdatePlanTypeCommand;
import com.agrocontrol.backend.subscription.domain.model.valueobjects.PlanTypes;
import com.agrocontrol.backend.subscription.domain.model.valueobjects.UserId;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;

import java.time.LocalDate;


@Getter
@Entity
public class Subscription extends AuditableAbstractAggregateRoot<Subscription> {

    private PlanTypes planType;

    @Embedded
    private UserId userId;

    private LocalDate startDate;

    private LocalDate renewalDate;

    private String status;

    private double cost;

    protected Subscription() {}

    public Subscription(CreateSubscriptionCommand command) {
        this.planType = command.planType();
        this.userId = new UserId(command.userId());
        this.startDate = LocalDate.now();
        this.renewalDate = LocalDate.now().plusMonths(1);
        this.status = command.status();
        this.cost = command.cost();
    }

    public void updatePlanType(UpdatePlanTypeCommand command) {
        this.planType = command.planType();
    }

    public void renewPlan(RenewSubscriptionCommand command) {
        this.renewalDate = command.renewalDate();
    }

    public Long getUserId() {
        return this.userId.userId();
    }

}
