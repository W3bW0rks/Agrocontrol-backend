package com.agrocontrol.backend.subscription.interfaces.rest;

import com.agrocontrol.backend.subscription.domain.model.aggregates.Payment;
import com.agrocontrol.backend.subscription.domain.model.queries.GetPaymentByIdQuery;
import com.agrocontrol.backend.subscription.domain.model.queries.GetPaymentBySubscriptionIdQuery;
import com.agrocontrol.backend.subscription.domain.model.queries.GetPaymentByUserIdQuery;
import com.agrocontrol.backend.subscription.domain.model.valueobjects.SubscriptionId;
import com.agrocontrol.backend.subscription.domain.model.valueobjects.UserId;
import com.agrocontrol.backend.subscription.domain.services.PaymentCommandService;
import com.agrocontrol.backend.subscription.domain.services.PaymentQueryService;
import com.agrocontrol.backend.subscription.interfaces.rest.resources.CreatePaymentResource;
import com.agrocontrol.backend.subscription.interfaces.rest.resources.PaymentResource;
import com.agrocontrol.backend.subscription.interfaces.rest.resources.RenewPaymentResource;
import com.agrocontrol.backend.subscription.interfaces.rest.resources.UpdatePlantTypeResource;
import com.agrocontrol.backend.subscription.interfaces.rest.transform.CreatePaymentCommandFromResourceAssembler;
import com.agrocontrol.backend.subscription.interfaces.rest.transform.PaymentResourceFromEntityAssembler;
import com.agrocontrol.backend.subscription.interfaces.rest.transform.RenewPaymentCommandFromResourceAssembler;
import com.agrocontrol.backend.subscription.interfaces.rest.transform.UpdatePlanTypeCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
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

    /**
     * Create a payment
     * @param resource Payment resource
     * @return Payment resource
     * @see CreatePaymentResource
     * @see PaymentResource
     */
    @Operation(summary = "Create a payment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Payment created"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PostMapping
    public ResponseEntity<PaymentResource> createPayment(@RequestBody CreatePaymentResource resource) {
        Optional<Payment> payment = this.paymentCommandService
                .handle(CreatePaymentCommandFromResourceAssembler.toCommandFromResource(resource));

        return payment.map(source -> new ResponseEntity<>(PaymentResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Retrieve a payment by id
     * @param id Payment id
     * @param resource Payment resource
     * @return Payment resource
     * @see PaymentResource
     */
    @Operation(summary = "Renew a payment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment renewed"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PutMapping("/{id}/renew")
    public ResponseEntity<PaymentResource> renewPayment(@PathVariable Long id, @RequestBody RenewPaymentResource resource) {
        var command = RenewPaymentCommandFromResourceAssembler.toCommandFromResource(resource, id);
        Optional<Payment> payment = this.paymentCommandService.handle(command);

        return payment.map(source -> new ResponseEntity<>(PaymentResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Update payment plan type
     * @param id Payment id
     * @param resource Payment resource
     * @return Payment resource
     * @see UpdatePlantTypeResource
     */
    @Operation(summary = "Update payment plan type")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment plan type updated"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PutMapping("/{id}/update-plan-type")
    public ResponseEntity<PaymentResource> updatePlanType(@PathVariable Long id, @RequestBody UpdatePlantTypeResource resource) {
        var command = UpdatePlanTypeCommandFromResourceAssembler.toCommandFromResource(resource, id);
        Optional<Payment> payment = this.paymentCommandService.handle(command);

        return payment.map(source -> new ResponseEntity<>(PaymentResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Get payment by id
     * @param id Payment id
     * @return Payment resource
     */
    @Operation(summary = "Get payment by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment found"),
        @ApiResponse(responseCode = "404", description = "Payment not found"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<PaymentResource> getPaymentById(@PathVariable Long id) {
        var query = new GetPaymentByIdQuery(id);
        Optional<Payment> payment = this.paymentQueryService.handle(query);

        return payment.map(source -> new ResponseEntity<>(PaymentResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Get payment by subscription id
     * @param subscriptionId Subscription id
     * @return Payment resource
     */
    @Operation(summary = "Get payment by subscription id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment found"),
        @ApiResponse(responseCode = "404", description = "Payment not found"),
    })
    @GetMapping("/subscription/{subscriptionId}")
    public ResponseEntity<PaymentResource> getPaymentBySubscriptionId(@PathVariable Long subscriptionId) {
        var paymentSubscriptionId = new SubscriptionId(subscriptionId);
        var query = new GetPaymentBySubscriptionIdQuery(paymentSubscriptionId);
        Optional<Payment> payment = this.paymentQueryService.handle(query);

        return payment.map(source -> new ResponseEntity<>(PaymentResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get payment by user id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment found"),
        @ApiResponse(responseCode = "404", description = "Payment not found"),
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<PaymentResource> getPaymentByUserId(@PathVariable Long userId) {
        var paymentUserId = new UserId(userId);
        var query = new GetPaymentByUserIdQuery(paymentUserId);
        Optional<Payment> payment = this.paymentQueryService.handle(query);

        return payment.map(source -> new ResponseEntity<>(PaymentResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
