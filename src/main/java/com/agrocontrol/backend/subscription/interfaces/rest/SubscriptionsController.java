package com.agrocontrol.backend.subscription.interfaces.rest;

import com.agrocontrol.backend.subscription.domain.model.aggregates.Subscription;
import com.agrocontrol.backend.subscription.domain.model.queries.GetSubscriptionByUserIdQuery;
import com.agrocontrol.backend.subscription.domain.model.valueobjects.UserId;
import com.agrocontrol.backend.subscription.domain.services.SubscriptionCommandService;
import com.agrocontrol.backend.subscription.domain.services.SubscriptionQueryService;
import com.agrocontrol.backend.subscription.interfaces.rest.resources.CreateSubscriptionResource;
import com.agrocontrol.backend.subscription.interfaces.rest.resources.SubscriptionResource;
import com.agrocontrol.backend.subscription.interfaces.rest.resources.UpdatePlantTypeResource;
import com.agrocontrol.backend.subscription.interfaces.rest.transform.CreateSubscriptionCommandFromResourceAssembler;
import com.agrocontrol.backend.subscription.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/subscriptions", produces = APPLICATION_JSON_VALUE)
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
    @Operation(summary = "Create a subscription")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Subscription created"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PostMapping
    public ResponseEntity<SubscriptionResource> createSubscription(@RequestBody CreateSubscriptionResource resource) {
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
     *//*
    @Operation(summary = "Renew a subscription")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Subscription renewed"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PutMapping("/{id}/renew")
    public ResponseEntity<SubscriptionResource> renewSubscription(@PathVariable Long id, @RequestBody RenewSubscriptionResource resource) {
        var command = RenewSubscriptionCommandFromResourceAssembler.toCommandFromResource(resource, id);
        Optional<Subscription> payment = this.paymentCommandService.handle(command);

        return payment.map(source -> new ResponseEntity<>(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    *//**
     * Update payment plan type
     * @param id Payment id
     * @param resource Payment resource
     * @return Payment resource
     * @see UpdatePlantTypeResource
     *//*
    @Operation(summary = "Update subscription plan type")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Subscription plan type updated"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PutMapping("/{id}/update-plan-type")
    public ResponseEntity<SubscriptionResource> updatePlanType(@PathVariable Long id, @RequestBody UpdatePlantTypeResource resource) {
        var command = UpdatePlanTypeCommandFromResourceAssembler.toCommandFromResource(resource, id);
        Optional<Subscription> payment = this.paymentCommandService.handle(command);

        return payment.map(source -> new ResponseEntity<>(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    *//**
     * Get payment by id
     * @param id Payment id
     * @return Payment resource
     *//*
    @Operation(summary = "Get subscription by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Subscription found"),
        @ApiResponse(responseCode = "404", description = "Subscription not found"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<SubscriptionResource> getSubscriptionById(@PathVariable Long id) {
        var query = new GetSubscriptionByIdQuery(id);
        Optional<Subscription> payment = this.paymentQueryService.handle(query);

        return payment.map(source -> new ResponseEntity<>(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }*/

    /**
     * Get subscription by user id
     * @param userId User id
     * @return Subscription resource
     */
    @Operation(summary = "Get subscription by user id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Subscription found"),
        @ApiResponse(responseCode = "404", description = "Subscription not found"),
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<SubscriptionResource> getSubscriptionByUserId(@PathVariable Long userId) {
        var paymentUserId = new UserId(userId);
        var query = new GetSubscriptionByUserIdQuery(paymentUserId);
        Optional<Subscription> payment = this.paymentQueryService.handle(query);

        return payment.map(source -> new ResponseEntity<>(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
