package com.agrocontrol.backend.agriculturalProcess.interfaces.rest;

import com.agrocontrol.backend.agriculturalProcess.domain.model.aggregates.AgriculturalProcess;
import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddIrrigationToProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddSeedingToProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.domain.model.queries.GetAgriculturalProcessByFieldIdQuery;
import com.agrocontrol.backend.agriculturalProcess.domain.model.queries.GetAgriculturalProcessByIdQuery;
import com.agrocontrol.backend.agriculturalProcess.domain.services.AgriculturalProcessCommandService;
import com.agrocontrol.backend.agriculturalProcess.domain.services.AgriculturalProcessQueryService;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources.AgriculturalProcessResource;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources.CreateAgriculturalProcessResource;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources.FinishAgriculturalProcessResource;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.transform.AgriculturalProcessResourceFromEntityAssembler;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.transform.CreateAgriculturalProcessCommandFromResourceAssembler;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.transform.FinishAgriculturalProcessCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/agricultural-processes", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Agricultural Processes", description = "Operations related to agricultural processes")
public class AgriculturalProcessesController {
    private final AgriculturalProcessCommandService commandService;
    private final AgriculturalProcessQueryService queryService;

    public AgriculturalProcessesController(AgriculturalProcessCommandService commandService, AgriculturalProcessQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @Operation(
            summary = "Create a new agricultural process",
            description = "Create a new agricultural process with the given data"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Agricultural process created"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<AgriculturalProcessResource> createAgriculturalProcess(
            @RequestBody CreateAgriculturalProcessResource resource) {
        Optional<AgriculturalProcess> agriculturalProcess = this.commandService
                .handle(CreateAgriculturalProcessCommandFromResourceAssembler.toCommandFromResource(resource));

        return agriculturalProcess.map(source ->
                        new ResponseEntity<>(AgriculturalProcessResourceFromEntityAssembler.toResourceFromEntity(source),
                                HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Add activity to an agricultural process",
            description = "Add an activity to an agricultural process with the provided parameters"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Activity added to the agricultural process"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/add-activity")
    public ResponseEntity<AgriculturalProcessResource> addActivityToAgriculturalProcess(
            @RequestParam Long agriculturalProcessId,
            @RequestParam(required = false) Integer hoursIrrigated,
            @RequestParam(required = false) String plantType,
            @RequestParam(required = false) Integer quantityPlanted) {

        if (hoursIrrigated != null) {
            return addIrrigationToProcess(hoursIrrigated, agriculturalProcessId);
        } else if (plantType != null && quantityPlanted != null) {
            return addSeedingToProcess(plantType, quantityPlanted, agriculturalProcessId);
        } else {
            return ResponseEntity.badRequest().body(null); // Bad request si faltan parámetros
        }
    }

    private ResponseEntity<AgriculturalProcessResource> addIrrigationToProcess(Integer hoursIrrigated, Long agriculturalProcessId) {
        var command = new AddIrrigationToProcessCommand(hoursIrrigated, agriculturalProcessId);
        var agriculturalProcess = this.commandService.handle(command);

        return agriculturalProcess.map(source ->
                        new ResponseEntity<>(AgriculturalProcessResourceFromEntityAssembler.toResourceFromEntity(source),
                                HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    private ResponseEntity<AgriculturalProcessResource> addSeedingToProcess(String plantType, Integer quantityPlanted, Long agriculturalProcessId) {
        var command = new AddSeedingToProcessCommand(plantType, quantityPlanted, agriculturalProcessId);
        var agriculturalProcess = this.commandService.handle(command);

        return agriculturalProcess.map(source ->
                        new ResponseEntity<>(AgriculturalProcessResourceFromEntityAssembler.toResourceFromEntity(source),
                                HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Finish an agricultural process",
            description = "Finish an agricultural process with the provided parameters"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Agricultural process finished"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping("/finish")
    public ResponseEntity<AgriculturalProcessResource> finishAgriculturalProcess(@RequestBody FinishAgriculturalProcessResource resource) {
        var agriculturalProcess = this.commandService.handle(FinishAgriculturalProcessCommandFromResourceAssembler.toCommandFromResource(resource));
        return agriculturalProcess.map(source ->
                        new ResponseEntity<>(AgriculturalProcessResourceFromEntityAssembler.toResourceFromEntity(source),
                                HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get an agricultural process by its id",
            description = "Get an agricultural process by its id"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Agricultural process found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping("/{agriculturalProcessId}")
    public ResponseEntity<AgriculturalProcessResource> getAgriculturalProcessById(@PathVariable Long agriculturalProcessId) {
        var query = new GetAgriculturalProcessByIdQuery(agriculturalProcessId);
        var agriculturalProcess = this.queryService.handle(query);

        return agriculturalProcess.map(source ->
                        new ResponseEntity<>(AgriculturalProcessResourceFromEntityAssembler.toResourceFromEntity(source),
                                HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Get an agricultural process by its field id",
            description = "Get an agricultural process by its field id"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Agricultural process found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping("/field/{fieldId}")
    public ResponseEntity<AgriculturalProcessResource> getAgriculturalProcessByFieldId(@PathVariable Long fieldId) {
        var query = new GetAgriculturalProcessByFieldIdQuery(fieldId);
        var agriculturalProcess = this.queryService.handle(query);

        return agriculturalProcess.map(source ->
                        new ResponseEntity<>(AgriculturalProcessResourceFromEntityAssembler.toResourceFromEntity(source),
                                HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
