package com.agrocontrol.backend.subscription.application.internal.commandservices;

import com.agrocontrol.backend.subscription.domain.model.aggregates.Payment;
import com.agrocontrol.backend.subscription.domain.model.commands.CreatePaymentCommand;
import com.agrocontrol.backend.subscription.domain.model.commands.RenewPaymentCommand;
import com.agrocontrol.backend.subscription.domain.model.commands.UpdatePlanTypeCommand;
import com.agrocontrol.backend.subscription.domain.model.valueobjects.PlanTypes;
import com.agrocontrol.backend.subscription.domain.services.PaymentCommandService;
import com.agrocontrol.backend.subscription.infrastructure.persistence.jpa.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {
    private final PaymentRepository paymentRepository;

    public PaymentCommandServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Optional<Payment> handle(CreatePaymentCommand command) {
        validatePlanType(command.planType().name());

        if (command.startDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Start date cannot be in the past");
        }

        if(command.renewalDate().isBefore(command.startDate())) {
            throw new IllegalArgumentException("Renewal date cannot be before start date");
        }
        var payment = new Payment(command);
        var createdPayment = paymentRepository.save(payment);
        return Optional.of(createdPayment);
    }

    @Override
    public Optional<Payment> handle(RenewPaymentCommand command) {
        if (command.renewalDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Renewal date cannot be in the past");
        }

        Payment payment = this.paymentRepository.findById(command.paymentId())
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        payment.renewPlan(command);
        var renewedPayment = paymentRepository.save(payment);
        return Optional.of(renewedPayment);
    }

    @Override
    public Optional<Payment> handle(UpdatePlanTypeCommand command) {
        validatePlanType(command.planType().name());

        Payment payment = this.paymentRepository.findById(command.paymentId())
                .orElseThrow(() -> new IllegalArgumentException("Payment not found"));

        payment.updatePlanType(command);
        var updatedPayment = paymentRepository.save(payment);
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