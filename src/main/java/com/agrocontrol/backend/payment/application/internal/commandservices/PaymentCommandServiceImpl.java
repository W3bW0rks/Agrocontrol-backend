package com.agrocontrol.backend.payment.application.internal.commandservices;

import com.agrocontrol.backend.payment.domain.model.aggregates.Payment;
import com.agrocontrol.backend.payment.domain.model.commands.CreatePaymentCommand;
import com.agrocontrol.backend.payment.domain.services.PaymentCommandService;
import com.agrocontrol.backend.payment.infrastructure.persistence.jpa.repositories.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {
    private final PaymentRepository paymentRepository;

    public PaymentCommandServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Optional<Payment> handle(CreatePaymentCommand command) {
        var payment = new Payment(command);
        var createdPayment = paymentRepository.save(payment);
        return Optional.of(createdPayment);


    }



}
