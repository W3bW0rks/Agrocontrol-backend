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
        if(command.cardNumber() == null || command.cardNumber().length() != 16){
            throw new IllegalArgumentException("Card number must be 16 digits");
        }
        if(command.cvv() == null || command.cvv().length() != 3){
            throw new IllegalArgumentException("CVV must be 3 digits");
        }
        if(command.cardHolderName() == null || command.cardHolderName().length() < 3){
            throw new IllegalArgumentException("Card holder name must be at least 3 characters");
        }
        if(command.ExpireDate() == null || command.ExpireDate().length() != 5){
            throw new IllegalArgumentException("Expire date must be in the format MM/YY");
        }

        var payment = new Payment(command);
        var createdPayment = paymentRepository.save(payment);
        return Optional.of(createdPayment);



    }



}
