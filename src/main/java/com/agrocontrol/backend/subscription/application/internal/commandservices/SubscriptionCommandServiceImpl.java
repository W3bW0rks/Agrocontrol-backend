package com.agrocontrol.backend.subscription.application.internal.commandservices;

import com.agrocontrol.backend.subscription.domain.model.aggregates.Subscription;
import com.agrocontrol.backend.subscription.domain.model.commands.CreateSubscriptionCommand;
import com.agrocontrol.backend.subscription.domain.model.commands.RenewSubscriptionCommand;
import com.agrocontrol.backend.subscription.domain.model.commands.UpdatePlanTypeCommand;
import com.agrocontrol.backend.subscription.domain.model.valueobjects.PlanTypes;
import com.agrocontrol.backend.subscription.domain.services.SubscriptionCommandService;
import com.agrocontrol.backend.subscription.infrastructure.persistence.jpa.repositories.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class SubscriptionCommandServiceImpl implements SubscriptionCommandService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionCommandServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Optional<Subscription> handle(CreateSubscriptionCommand command) {
        validatePlanType(command.planType().name());

        var payment = new Subscription(command);
        var createdPayment = subscriptionRepository.save(payment);
        return Optional.of(createdPayment);
    }

    @Override
    public Optional<Subscription> handle(RenewSubscriptionCommand command) {
        if (command.renewalDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Renewal date cannot be in the past");
        }

        Subscription payment = this.subscriptionRepository.findById(command.paymentId())
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        payment.renewPlan(command);
        var renewedPayment = subscriptionRepository.save(payment);
        return Optional.of(renewedPayment);
    }

    @Override
    public Optional<Subscription> handle(UpdatePlanTypeCommand command) {
        validatePlanType(command.planType().name());

        Subscription payment = this.subscriptionRepository.findById(command.paymentId())
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        payment.updatePlanType(command);
        var updatedPayment = subscriptionRepository.save(payment);
        return Optional.of(updatedPayment);
    }

    private void validatePlanType(String planType) {
        try {
            PlanTypes.valueOf(planType);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid plan type");
        }
    }
}