package com.agrocontrol.backend.subscription.interfaces.rest;

import com.agrocontrol.backend.subscription.domain.model.aggregates.Subscription;
import com.agrocontrol.backend.subscription.domain.model.queries.GetSubscriptionByIdQuery;
import com.agrocontrol.backend.subscription.domain.model.queries.GetSubscriptionByUserIdQuery;
import com.agrocontrol.backend.subscription.domain.model.valueobjects.UserId;
import com.agrocontrol.backend.subscription.domain.services.SubscriptionCommandService;
import com.agrocontrol.backend.subscription.domain.services.SubscriptionQueryService;
import com.agrocontrol.backend.subscription.interfaces.rest.resources.CreateSubscriptionResource;
import com.agrocontrol.backend.subscription.interfaces.rest.resources.SubscriptionResource;
import com.agrocontrol.backend.subscription.interfaces.rest.resources.RenewSubscriptionResource;
import com.agrocontrol.backend.subscription.interfaces.rest.resources.UpdatePlantTypeResource;
import com.agrocontrol.backend.subscription.interfaces.rest.transform.CreateSubscriptionCommandFromResourceAssembler;
import com.agrocontrol.backend.subscription.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;
import com.agrocontrol.backend.subscription.interfaces.rest.transform.RenewSubscriptionCommandFromResourceAssembler;
import com.agrocontrol.backend.subscription.interfaces.rest.transform.UpdatePlanTypeCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/subscription", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Subscription", description = "Operations related to subscription")
public class SubscriptionsController {
    private final SubscriptionCommandService paymentCommandService;
    private final SubscriptionQueryService paymentQueryService;

    public SubscriptionsController(SubscriptionCommandService paymentCommandService, SubscriptionQueryService paymentQueryService) {
        this.paymentCommandService = paymentCommandService;
        this.paymentQueryService = paymentQueryService;
    }

    /**
     * Create a payment
     * @param resource Payment resource
     * @return Payment resource
     * @see CreateSubscriptionResource
     * @see SubscriptionResource
     */
    @Operation(summary = "Create a payment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Payment created"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PostMapping
    public ResponseEntity<SubscriptionResource> createPayment(@RequestBody CreateSubscriptionResource resource) {
        Optional<Subscription> payment = this.paymentCommandService
                .handle(CreateSubscriptionCommandFromResourceAssembler.toCommandFromResource(resource));

        return payment.map(source -> new ResponseEntity<>(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Retrieve a payment by id
     * @param id Payment id
     * @param resource Payment resource
     * @return Payment resource
     * @see SubscriptionResource
     */
    @Operation(summary = "Renew a payment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment renewed"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PutMapping("/{id}/renew")
    public ResponseEntity<SubscriptionResource> renewPayment(@PathVariable Long id, @RequestBody RenewSubscriptionResource resource) {
        var command = RenewSubscriptionCommandFromResourceAssembler.toCommandFromResource(resource, id);
        Optional<Subscription> payment = this.paymentCommandService.handle(command);

        return payment.map(source -> new ResponseEntity<>(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
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
    public ResponseEntity<SubscriptionResource> updatePlanType(@PathVariable Long id, @RequestBody UpdatePlantTypeResource resource) {
        var command = UpdatePlanTypeCommandFromResourceAssembler.toCommandFromResource(resource, id);
        Optional<Subscription> payment = this.paymentCommandService.handle(command);

        return payment.map(source -> new ResponseEntity<>(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
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
    public ResponseEntity<SubscriptionResource> getPaymentById(@PathVariable Long id) {
        var query = new GetSubscriptionByIdQuery(id);
        Optional<Subscription> payment = this.paymentQueryService.handle(query);

        return payment.map(source -> new ResponseEntity<>(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get payment by user id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Payment found"),
        @ApiResponse(responseCode = "404", description = "Payment not found"),
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<SubscriptionResource> getPaymentByUserId(@PathVariable Long userId) {
        var paymentUserId = new UserId(userId);
        var query = new GetSubscriptionByUserIdQuery(paymentUserId);
        Optional<Subscription> payment = this.paymentQueryService.handle(query);

        return payment.map(source -> new ResponseEntity<>(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
