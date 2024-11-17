package com.agrocontrol.backend.store.interfaces.rest;

import com.agrocontrol.backend.store.domain.model.aggregates.Product;
import com.agrocontrol.backend.store.domain.model.queries.GetProductByUserIdQuery;
import com.agrocontrol.backend.store.domain.model.queries.GetProductsNotOwnedByUserIdQuery;
import com.agrocontrol.backend.store.domain.services.ProductCommandService;
import com.agrocontrol.backend.store.domain.services.ProductQueryService;
import com.agrocontrol.backend.store.interfaces.rest.resources.*;
import com.agrocontrol.backend.store.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/products", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Products", description = "Operations related to products")
public class ProductsController {

    private final ProductCommandService productCommandService;
    private final ProductQueryService productQueryService;

    public ProductsController(ProductCommandService productCommandService, ProductQueryService productQueryService) {
        this.productCommandService = productCommandService;
        this.productQueryService = productQueryService;
    }

    @Operation(summary = "Create a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Product created"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PostMapping
    public ResponseEntity<ProductResource> createPayment(@RequestBody CreateProductResource resource) {
        Optional<Product> product = this.productCommandService
                .handle(CreateProductCommandFromResourceAssembler.toCommandFromResource(resource));

        return product.map(source -> new ResponseEntity<>(ProductResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PutMapping("/{id}")
    public ResponseEntity<ProductResource> updateProduct(@PathVariable Long id, @RequestBody UpdateProductResource resource) {
        Optional<Product> product = this.productCommandService
                .handle(UpdateProductCommandFromResourceAssembler.toCommandFromResource(resource, id));

        return product.map(source -> new ResponseEntity<>(ProductResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update quantity of a product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product quantity updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PutMapping("/{id}/update-quantity")
    public ResponseEntity<ProductResource> updateProductQuantity(@PathVariable Long id, @RequestBody ChangeQuantityOfProductResource resource) {
        Optional<Product> product = this.productCommandService
                .handle(ChangeQuantityOfProductCommandFromResourceAssembler.toCommandFromResource(resource, id));

        return product.map(source -> new ResponseEntity<>(ProductResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Update a product owner")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product owner updated"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
    })
    @PutMapping("/update-product-owner")
    public ResponseEntity<ProductResource> updateProductOwner(@RequestBody UpdateProductOwnerResource resource) {
        Optional<Product> product = this.productCommandService
                .handle(UpdateProductOwnerCommandFromResourceAssembler.toCommandFromResource(resource));

        return product.map(source -> new ResponseEntity<>(ProductResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /*
    @Operation(summary = "Get product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductResource> getProductById(@PathVariable Long id) {
        var query = new GetProductByIdQuery(id);

        var product = this.productQueryService.handle(query);

        return product.map(source -> new ResponseEntity<>(ProductResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Get product by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
    })
    @GetMapping("/name/{name}")
    public ResponseEntity<ProductResource> getProductByName(@PathVariable String name) {
        var query = new GetProductByNameQuery(name);

        var product = this.productQueryService.handle(query);

        return product.map(source -> new ResponseEntity<>(ProductResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    } */

    @Operation(summary = "Get product by user id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product found"),
            @ApiResponse(responseCode = "404", description = "Product not found"),
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<ProductResource>> getProductByUserId(@PathVariable Long userId) {
        var query = new GetProductByUserIdQuery(userId);

        List<Product> products = this.productQueryService.handle(query);

        if (products.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<ProductResource> resources = products.stream()
                .map(ProductResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(resources);
    }

    @Operation(summary = "Get all products not owned by user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products found"),
            @ApiResponse(responseCode = "404", description = "Products not found"),
    })
    @GetMapping("/{userId}")
    public ResponseEntity<List<ProductResource>> getAllProducts(@PathVariable Long userId) {
        List<Product> products = this.productQueryService.handle(new GetProductsNotOwnedByUserIdQuery(userId));

        if (products.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        List<ProductResource> resources = products.stream()
                .map(ProductResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(resources);
    }
}
