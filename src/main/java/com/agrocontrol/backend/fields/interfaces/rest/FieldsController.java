package com.agrocontrol.backend.fields.interfaces.rest;

import com.agrocontrol.backend.fields.domain.model.aggregates.Field;
import com.agrocontrol.backend.fields.domain.model.commands.DeleteFieldCommand;
import com.agrocontrol.backend.fields.domain.model.queries.GetFieldsByProducerIdQuery;
import com.agrocontrol.backend.fields.domain.services.FieldCommandService;
import com.agrocontrol.backend.fields.domain.services.FieldQueryService;
import com.agrocontrol.backend.fields.interfaces.rest.resources.CreateFieldResource;
import com.agrocontrol.backend.fields.interfaces.rest.resources.FieldResource;
import com.agrocontrol.backend.fields.interfaces.rest.resources.UpdateFieldResource;
import com.agrocontrol.backend.fields.interfaces.rest.transform.CreateFieldCommandFromFieldResourceAssembler;
import com.agrocontrol.backend.fields.interfaces.rest.transform.FieldResourceFromEntityAssembler;
import com.agrocontrol.backend.fields.interfaces.rest.transform.UpdateFieldCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/fields",produces= APPLICATION_JSON_VALUE)
@Tag(name="Fields",description = "Operations related to fields")
public class FieldsController {
    private final FieldCommandService fieldCommandService;
    private final FieldQueryService fieldQueryService;

    public FieldsController(FieldCommandService fieldCommandService, FieldQueryService fieldQueryService) {
        this.fieldCommandService = fieldCommandService;
        this.fieldQueryService = fieldQueryService;
    }

    /**
     * Create a field
     * @param resource Field Resource
     * @return Field Resource
     * @see CreateFieldResource
     * @see FieldResource
     */
    @Operation(summary = "Create a field")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Field created"),
            @ApiResponse(responseCode = "400", description = "Invalid Input"),
    })
    @PostMapping
    public ResponseEntity<FieldResource> createField(@RequestBody CreateFieldResource resource) {
        Optional<Field> field = this.fieldCommandService
                .handle(CreateFieldCommandFromFieldResourceAssembler.toCommandFromFieldResource(resource));
        return field.map(source->new ResponseEntity<>(FieldResourceFromEntityAssembler.toResourceFromEntity(source),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    /**
     * Update an existing field.
     *
     * @param id       the ID of the field to update
     * @param resource the resource containing updated field details
     * @return a response entity containing the updated field resource or a bad request status
     * @see UpdateFieldResource
     * @see FieldResource
     */
    @Operation(summary = "Update a field")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Field updated"),
            @ApiResponse(responseCode = "400", description = "Invalid Input")
    })
    @PutMapping("/{id}/update-field")
    public ResponseEntity<FieldResource> updateField(@PathVariable Long id, @RequestBody UpdateFieldResource resource) {
        var command = UpdateFieldCommandFromResourceAssembler.toCommandFromResource(resource,id);
        Optional<Field> field = this.fieldCommandService.handle(command);
        return field.map(source-> new ResponseEntity<>(FieldResourceFromEntityAssembler.toResourceFromEntity(source),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }

    /*
    /**
     * Get a field by its ID.
     *
     * @param id the ID of the field to retrieve
     * @return a response entity containing the field resource or a bad request status
     *//*
    @Operation(summary = "Get field by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Fields found"),
            @ApiResponse(responseCode = "400",description = "Fields not found")
    })
    @GetMapping("/{fieldId}")
    public ResponseEntity<FieldResource> getFieldById(@PathVariable Long fieldId){
        var query = new GetFieldByIdQuery(fieldId);
        Optional<Field> field = this.fieldQueryService.handle(query);
        return field.map(source->new ResponseEntity<>(FieldResourceFromEntityAssembler.toResourceFromEntity(source),CREATED))
                .orElseGet(()->ResponseEntity.badRequest().build());
    }*/


    /**
     * Get all fields associated with a producer.
     *
     * @param userId the ID of the producer
     * @return a response entity containing a list of field resources or a bad request status
     */

    @Operation(summary = "Get fields by producer ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Fields found"),
            @ApiResponse(responseCode = "400",description = "Fields not found")
    })
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FieldResource>> getFieldByUserId(@PathVariable Long userId){
        var query= new GetFieldsByProducerIdQuery(userId);
        List<Field> fields = this.fieldQueryService.handle(query);
        if(fields.isEmpty()){
            return ResponseEntity.badRequest().build();
        }
        List<FieldResource> fieldResources = fields.stream()
                .map(FieldResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return new ResponseEntity<>(fieldResources,CREATED);
    }

    /**
     * Delete a field by its ID and producer ID.
     *
     * @param id         the ID of the field to delete
     * @param producerId the ID of the producer associated with the field
     * @return a response entity indicating successful deletion or a bad request status
     */
    @Operation(summary = "Delete field by a field ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Field deleted"),
            @ApiResponse(responseCode = "400",description = "Field not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFieldById(@PathVariable Long id, @RequestParam Long producerId) {

        DeleteFieldCommand command = new DeleteFieldCommand(id, producerId);
        this.fieldCommandService.handle(command);
        return ResponseEntity.noContent().build();
    }



}
