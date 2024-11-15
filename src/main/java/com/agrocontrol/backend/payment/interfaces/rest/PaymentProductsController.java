package com.agrocontrol.backend.payment.interfaces.rest;

import com.agrocontrol.backend.payment.domain.model.commands.CreatePaymentProductCommand;
import com.agrocontrol.backend.payment.domain.model.queries.GetPaymentProductByOwnerProductId;
import com.agrocontrol.backend.payment.domain.services.PaymentProductCommandService;
import com.agrocontrol.backend.payment.domain.services.PaymentProductQueryService;
import com.agrocontrol.backend.payment.interfaces.rest.resources.CreatePaymentProductResource;
import com.agrocontrol.backend.payment.interfaces.rest.resources.PaymentProductHistoryResource;
import com.agrocontrol.backend.payment.interfaces.rest.resources.PaymentProductResource;
import com.agrocontrol.backend.payment.interfaces.rest.transform.CreatePaymentProductCommandFromResourceAssembler;
import com.agrocontrol.backend.payment.interfaces.rest.transform.PaymentProductHistoryResourceFromEntityAssembler;
import com.agrocontrol.backend.payment.interfaces.rest.transform.PaymentProductResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/payment-products", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Payments", description = "Operations related to payments")
public class PaymentProductsController {
    private final PaymentProductCommandService paymentProductCommandService;
    private final PaymentProductQueryService paymentProductQueryService;

    public PaymentProductsController(PaymentProductCommandService paymentProductCommandService, PaymentProductQueryService paymentProductQueryService) {
        this.paymentProductCommandService = paymentProductCommandService;
        this.paymentProductQueryService = paymentProductQueryService;
    }

    @Operation(summary = "Create a payment product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment product created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PostMapping
    public ResponseEntity<PaymentProductResource> createPaymentProduct(@RequestBody CreatePaymentProductResource resource) {
        var createPaymentProductCommand = CreatePaymentProductCommandFromResourceAssembler.toCommandFromResource(resource);
        var paymentProduct = paymentProductCommandService.handle(createPaymentProductCommand);
        if(paymentProduct.isEmpty()) return ResponseEntity.badRequest().build();
        var paymentProductResource = PaymentProductResourceFromEntityAssembler.toResourceFromEntity(paymentProduct.get());
        return new ResponseEntity<>(paymentProductResource, HttpStatus.CREATED);

    }

    @GetMapping(value = "/history-payments-products/{ownerProductId}")
    public ResponseEntity<List<PaymentProductHistoryResource>> getPaymentProductHistoryByOwnerProductId(@PathVariable Long ownerProductId) {
        var getPaymentProductByOwnerProductId = new GetPaymentProductByOwnerProductId(ownerProductId);
        var paymentsProducts = paymentProductQueryService.handle(getPaymentProductByOwnerProductId);
        var paymentProductHistoryResource = paymentsProducts.stream()
                .map(PaymentProductHistoryResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(paymentProductHistoryResource);
    }
}
