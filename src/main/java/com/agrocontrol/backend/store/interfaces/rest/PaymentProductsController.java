package com.agrocontrol.backend.store.interfaces.rest;

import com.agrocontrol.backend.store.domain.model.queries.GetPaymentProductByOwnerProductId;
import com.agrocontrol.backend.store.domain.model.queries.GetPaymentProductByUserIdQuery;
import com.agrocontrol.backend.store.domain.services.PaymentProductCommandService;
import com.agrocontrol.backend.store.domain.services.PaymentProductQueryService;
import com.agrocontrol.backend.store.interfaces.rest.resources.CreatePaymentProductResource;
import com.agrocontrol.backend.store.interfaces.rest.resources.PaymentProductResource;
import com.agrocontrol.backend.store.interfaces.rest.transform.CreatePaymentProductCommandFromResourceAssembler;
import com.agrocontrol.backend.store.interfaces.rest.transform.PaymentProductResourceFromEntityAssembler;
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
@Tag(name = "Store", description = "Operations related to store")
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

    @Operation(summary = "Get payment product by owner product id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment product found"),
            @ApiResponse(responseCode = "400", description = "Payment product not found"),
    })
    @GetMapping(value = "/owner-product/{ownerProductId}")
    public ResponseEntity<List<PaymentProductResource>> getPaymentProductByOwnerProductId(@PathVariable Long ownerProductId) {
        var getPaymentProductByOwnerProductId = new GetPaymentProductByOwnerProductId(ownerProductId);
        var paymentsProducts = paymentProductQueryService.handle(getPaymentProductByOwnerProductId);
        var PaymentProductResource = paymentsProducts.stream()
                .map(PaymentProductResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(PaymentProductResource);
    }

    @Operation(summary = "Get payment product by user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment product found"),
            @ApiResponse(responseCode = "400", description = "Payment product not found"),
    })
    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<PaymentProductResource>> getPaymentProductByUserId(@PathVariable Long userId) {
        var query = new GetPaymentProductByUserIdQuery(userId);
        var paymentsProducts = paymentProductQueryService.handle(query);
        var PaymentProductResource = paymentsProducts.stream()
                .map(PaymentProductResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(PaymentProductResource);
    }

}
