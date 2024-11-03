package com.agrocontrol.backend.finances.interfaces.rest;

import com.agrocontrol.backend.finances.domain.model.aggregates.Finance;
import com.agrocontrol.backend.finances.domain.model.queries.GetFinancesByAgriculturalProcessIdQuery;
import com.agrocontrol.backend.finances.domain.model.valueobjects.AgriculturalProcessId;
import com.agrocontrol.backend.finances.domain.services.FinanceCommandService;
import com.agrocontrol.backend.finances.domain.services.FinanceQueryService;
import com.agrocontrol.backend.finances.interfaces.rest.resources.CreateFinanceResource;
import com.agrocontrol.backend.finances.interfaces.rest.resources.FinanceResource;
import com.agrocontrol.backend.finances.interfaces.rest.transform.CreateFinanceCommandFromResourceAssembler;
import com.agrocontrol.backend.finances.interfaces.rest.transform.FinanceResourceFromEntityAssembler;
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
@RequestMapping(value = "/api/v1/finances", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Finances", description = "Operations related to finances")
public class FinancesController {

    private final FinanceCommandService financeCommandService;
    private final FinanceQueryService financeQueryService;

    public FinancesController(FinanceCommandService financeCommandService, FinanceQueryService financeQueryService) {
        this.financeCommandService = financeCommandService;
        this.financeQueryService = financeQueryService;
    }

    @Operation(summary = "Create a new finance", description = "Create a new finance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Finance created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<FinanceResource> createFinance(@RequestBody CreateFinanceResource resource) {
        Optional<Finance> finance = this.financeCommandService.handle(CreateFinanceCommandFromResourceAssembler.toCommandFromResource(resource));

        return finance.map(source -> new ResponseEntity<>(FinanceResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get a list of finance by agricultural process id",
            description = "Get a list of finance by agricultural process id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Finance found"),
            @ApiResponse(responseCode = "404", description = "Finance not found")
    })
    @GetMapping("/{agriculturalProcessId}")
    public ResponseEntity<List<FinanceResource>> getFinancesByAgriculturalProcessId(@PathVariable Long agriculturalProcessId) {
        var id = new AgriculturalProcessId(agriculturalProcessId);
        var query = new GetFinancesByAgriculturalProcessIdQuery(id);

        List<Finance> finances = this.financeQueryService.handle(query);

        if (finances.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<FinanceResource> resources = finances.stream()
                .map(FinanceResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(resources);
    }
}
