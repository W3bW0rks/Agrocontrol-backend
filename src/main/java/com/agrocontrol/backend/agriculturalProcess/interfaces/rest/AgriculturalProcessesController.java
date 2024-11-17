package com.agrocontrol.backend.agriculturalProcess.interfaces.rest;

import com.agrocontrol.backend.agriculturalProcess.domain.model.aggregates.AgriculturalProcess;
import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddCropTreatmentToProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddHarvestToProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddIrrigationToProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.domain.model.commands.AddSeedingToProcessCommand;
import com.agrocontrol.backend.agriculturalProcess.domain.model.queries.GetActivitiesByActivityTypeAndAgriculturalProcessIdQuery;
import com.agrocontrol.backend.agriculturalProcess.domain.model.queries.GetLastActivityByActivityTypeAndAgriculturalProcessIdQuery;
import com.agrocontrol.backend.agriculturalProcess.domain.model.queries.GetUnfinishedAgriculturalProcessByFieldIdQuery;
import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.ActivityType;
import com.agrocontrol.backend.agriculturalProcess.domain.model.valueobjects.AgriculturalActivity;
import com.agrocontrol.backend.agriculturalProcess.domain.services.AgriculturalProcessCommandService;
import com.agrocontrol.backend.agriculturalProcess.domain.services.AgriculturalProcessQueryService;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.resources.*;
import com.agrocontrol.backend.agriculturalProcess.interfaces.rest.transform.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<AgriculturalActivityResource> addActivityToAgriculturalProcess(
            @RequestParam Long agriculturalProcessId,
            @RequestParam String date,
            @RequestParam(required = false) Double hoursIrrigated,
            @RequestParam(required = false) String plantType,
            @RequestParam(required = false) Integer quantityPlanted,
            @RequestParam(required = false) String treatmentType,
            @RequestParam(required = false) Double quantityInKg,
            @RequestParam(required = false) Double pricePerKg) {

        // Check for irrigation activity
        if (hoursIrrigated != null && hoursIrrigated > 0) {
            return addIrrigationToProcess(date, hoursIrrigated, agriculturalProcessId);
        }
        // Check for seeding activity
        else if (plantType != null && quantityPlanted != null && quantityPlanted > 0) {
            return addSeedingToProcess(date, plantType, quantityPlanted, agriculturalProcessId);
        }
        // Check for crop treatment activity
        else if (treatmentType != null && !treatmentType.trim().isEmpty()) {
            return addCropTreatmentToProcess(date, treatmentType, agriculturalProcessId);
        }
        // Check for harvest activity
        else if (quantityInKg != null && quantityInKg > 0 && pricePerKg != null && pricePerKg > 0) {
            return addHarvestToProcess(date, quantityInKg, pricePerKg, agriculturalProcessId);
        }
        // If no valid parameters are found, return a bad request response
        else {
            return ResponseEntity.badRequest().build();
        }
    }


    private ResponseEntity<AgriculturalActivityResource> addHarvestToProcess(String date, double quantityInKg, double pricePerKg, Long agriculturalProcessId) {
        var command = new AddHarvestToProcessCommand(date, quantityInKg, pricePerKg, agriculturalProcessId);
        var agriculturalProcess = this.commandService.handle(command);

        return agriculturalProcess.map(source ->
                        new ResponseEntity<>(AgriculturalActivityResourceAssembler.toResourceFromEntity(source),
                                HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    private ResponseEntity<AgriculturalActivityResource> addCropTreatmentToProcess(String date, String treatmentType, Long agriculturalProcessId) {
        var command = new AddCropTreatmentToProcessCommand(date, treatmentType, agriculturalProcessId);
        var agriculturalProcess = this.commandService.handle(command);

        return agriculturalProcess.map(source ->
                        new ResponseEntity<>(AgriculturalActivityResourceAssembler.toResourceFromEntity(source),
                                HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    private ResponseEntity<AgriculturalActivityResource> addIrrigationToProcess(String date, double hours, Long agriculturalProcessId) {
        var hoursIrrigated = Integer.valueOf((int) hours);
        var command = new AddIrrigationToProcessCommand(date, hoursIrrigated, agriculturalProcessId);
        var agriculturalProcess = this.commandService.handle(command);

        return agriculturalProcess.map(source ->
                        new ResponseEntity<>(AgriculturalActivityResourceAssembler.toResourceFromEntity(source),
                                HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    private ResponseEntity<AgriculturalActivityResource> addSeedingToProcess(String date, String plantType, Integer quantityPlanted, Long agriculturalProcessId) {
        var command = new AddSeedingToProcessCommand(date, plantType, quantityPlanted, agriculturalProcessId);
        var agriculturalProcess = this.commandService.handle(command);

        return agriculturalProcess.map(source ->
                        new ResponseEntity<>(AgriculturalActivityResourceAssembler.toResourceFromEntity(source),
                                HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Add resource to an activity",
            description = "Add a resource to an activity with the provided parameters"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Resource added to the activity"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping("/activity/add-resource")
    public ResponseEntity<AgriculturalActivityResource> addResourceToActivity(@RequestBody AddResourceToActivityResource resource) {
        var activity = this.commandService.handle(AddResourceToActivityCommandFromResourceAssembler.toCommandFromResource(resource));
        return activity.map(source ->
                        new ResponseEntity<>(AgriculturalActivityResourceAssembler.toResourceFromEntity(source),
                                HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Find unfinished agricultural process by field id",
            description = "Find unfinished agricultural processes by field id"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Unfinished agricultural processes found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "No unfinished agricultural processes found")
    })
    @GetMapping("/field/{fieldId}/unfinished")
    public ResponseEntity<AgriculturalProcessResource> findUnfinishedAgriculturalProcessByFieldId(@PathVariable Long fieldId) {
        var query = new GetUnfinishedAgriculturalProcessByFieldIdQuery(fieldId);
        var agriculturalProcess = this.queryService.handle(query);

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
    @PutMapping("/finish/{agriculturalProcessId}")
    public ResponseEntity<AgriculturalProcessResource> finishAgriculturalProcess(@PathVariable Long agriculturalProcessId) {
        var agriculturalProcess = this.commandService.handle(FinishAgriculturalProcessCommandFromResourceAssembler.toCommandFromResource(agriculturalProcessId));
        return agriculturalProcess.map(source ->
                        new ResponseEntity<>(AgriculturalProcessResourceFromEntityAssembler.toResourceFromEntity(source),
                                HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(
            summary = "Execute an action in an agricultural activity",
            description = "Execute an action in an agricultural activity with the provided parameters"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Action executed"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PutMapping("/activity/{activityId}/execute")
    public ResponseEntity<AgriculturalActivityResource> executeActionInAgriculturalActivity(@PathVariable Long activityId,
                                                                                            @RequestBody ExecuteAgriculturalActivityActionResource resource) {
        var activity = this.commandService.handle(ExecuteAgriculturalActivityActionCommandFromResourceAssembler
                .toCommandFromResource(resource, activityId));
        return activity.map(source ->
                        new ResponseEntity<>(AgriculturalActivityResourceAssembler.toResourceFromEntity(source),
                                HttpStatus.CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /*
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
    public ResponseEntity<List<AgriculturalProcessResource>> getAgriculturalProcessByFieldId(@PathVariable Long fieldId) {
        var query = new GetAgriculturalProcessByFieldIdQuery(fieldId);
        List<AgriculturalProcess> agriculturalProcesses = this.queryService.handle(query);

        if (agriculturalProcesses.isEmpty()) return ResponseEntity.badRequest().build();

        List<AgriculturalProcessResource> resources = agriculturalProcesses.stream().map(AgriculturalProcessResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);

    }*/

    @Operation(
            summary = "Get activities by activity type and agricultural process id",
            description = "Get activities by activity type and agricultural process id"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Activities found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping("/{agriculturalProcessId}/activities/{activityType}")
    public ResponseEntity<List<AgriculturalActivityResource>> getActivitiesByActivityTypeAndAgriculturalProcessId(
            @PathVariable String activityType,
        @PathVariable Long agriculturalProcessId) {
        ActivityType type = ActivityType.valueOf(activityType);
        var query = new GetActivitiesByActivityTypeAndAgriculturalProcessIdQuery(type, agriculturalProcessId);
        List<AgriculturalActivity> activities = this.queryService.handle(query);

        if (activities.isEmpty()) return ResponseEntity.badRequest().build();

        List<AgriculturalActivityResource> resources = activities.stream()
                .map(AgriculturalActivityResourceAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(resources);
    }

    @Operation(
            summary = "Get last activity by activity type and agricultural process id",
            description = "Get last activity by activity type and agricultural process id"
    )
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Activity found"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad request")
    })
    @GetMapping("/{agriculturalProcessId}/lastActivity/{activityType}")
    public ResponseEntity<AgriculturalActivityResource> getLastActivityByActivityTypeAndAgriculturalProcessId(
            @PathVariable String activityType,
            @PathVariable Long agriculturalProcessId) {
        ActivityType type = ActivityType.valueOf(activityType);
        var query = new GetLastActivityByActivityTypeAndAgriculturalProcessIdQuery(agriculturalProcessId, type);
        var lastActivity = this.queryService.handle(query);

        return lastActivity.map(source ->
                        new ResponseEntity<>(AgriculturalActivityResourceAssembler.toResourceFromEntity(source),
                                HttpStatus.OK))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
