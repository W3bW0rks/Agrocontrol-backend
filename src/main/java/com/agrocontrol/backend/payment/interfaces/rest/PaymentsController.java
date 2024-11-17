package com.agrocontrol.backend.payment.interfaces.rest;


import com.agrocontrol.backend.payment.domain.model.aggregates.Payment;
import com.agrocontrol.backend.payment.domain.model.queries.GetPaymentBySubscriptionIdQuery;
import com.agrocontrol.backend.payment.domain.model.valueobjects.SubscriptionId;
import com.agrocontrol.backend.payment.domain.services.PaymentCommandService;
import com.agrocontrol.backend.payment.domain.services.PaymentQueryService;
import com.agrocontrol.backend.payment.interfaces.rest.resources.CreatePaymentResource;
import com.agrocontrol.backend.payment.interfaces.rest.resources.PaymentResource;
import com.agrocontrol.backend.payment.interfaces.rest.transform.CreatePaymentCommandFromResourceAssembler;
import com.agrocontrol.backend.payment.interfaces.rest.transform.PaymentResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/payments", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Payments", description = "Operations related to payments")
public class PaymentsController {
    private final PaymentCommandService paymentCommandService;
    private final PaymentQueryService paymentQueryService;

    public PaymentsController(PaymentCommandService paymentCommandService, PaymentQueryService paymentQueryService) {
        this.paymentCommandService = paymentCommandService;
        this.paymentQueryService = paymentQueryService;
    }



    @Operation(summary = "Create a payment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Payment created"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
  @PostMapping
    public ResponseEntity<PaymentResource> createPayment(@RequestBody CreatePaymentResource resource) {
        Optional<Payment> payment = this.paymentCommandService.handle(CreatePaymentCommandFromResourceAssembler.toCommandFromSource(resource));

        return payment.map(source -> new ResponseEntity<>(PaymentResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)).orElseGet(()
                -> ResponseEntity.badRequest().build());


    }

    /*
    @Operation(summary = "Get payment by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment found"),
            @ApiResponse(responseCode = "404", description = "Payment not found"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResource> getPaymentById(@PathVariable Long id) {
        var query = new GetPaymentByIdQuery(id);
        Optional<Payment> payment = this.paymentQueryService.handle(query);

        return payment.map(source -> new ResponseEntity<>(PaymentResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)).orElseGet(()
                -> ResponseEntity.notFound().build());
    }*/

    @Operation (summary = "Get payment by subscription id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment found"),
            @ApiResponse(responseCode = "404", description = "Payment not found"),
    })
    @GetMapping("/subscription/{subscriptionId}")
    public ResponseEntity<PaymentResource> getPaymentBySubscriptionId(@PathVariable Long subscriptionId) {
        var paymentSubscriptionId = new SubscriptionId(subscriptionId);
        var query = new GetPaymentBySubscriptionIdQuery(paymentSubscriptionId);
        Optional<Payment> payment = this.paymentQueryService.handle(query);

        return payment.map(source -> new ResponseEntity<>(PaymentResourceFromEntityAssembler.toResourceFromEntity(source), CREATED)).orElseGet(()
                -> ResponseEntity.notFound().build());
    }
}
