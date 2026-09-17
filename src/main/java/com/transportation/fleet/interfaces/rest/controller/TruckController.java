package com.transportation.fleet.interfaces.rest.controller;

import com.transportation.fleet.application.commandservices.TruckCommandService;
import com.transportation.fleet.application.queryservices.TruckQueryService;
import com.transportation.fleet.interfaces.rest.resources.CreateTruckResource;
import com.transportation.fleet.interfaces.rest.resources.TruckResource;
import com.transportation.fleet.interfaces.rest.transform.CreateTruckCommandFromResourceAssembler;
import com.transportation.fleet.interfaces.rest.transform.TruckResourceFromEntityAssembler;
import com.transportation.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/trucks",
                produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Trucks - Commands",
        description = "Commands management endpoints")
public class TruckController {
    private final TruckCommandService truckCommandService;
    private final TruckQueryService truckQueryService;

    public TruckController(TruckCommandService truckCommandService, TruckQueryService truckQueryService) {
        this.truckCommandService = truckCommandService;
        this.truckQueryService = truckQueryService;
    }



    @PostMapping
    @Operation(summary = "create truck",
               description = "Creates a new truck in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",
                        description = "Created",
                        content = @Content(
                                schema = @Schema(
                                        implementation = TruckResource.class)))
    })
    public ResponseEntity<?> createTruck(@Valid @RequestBody CreateTruckResource resource) {
        var createTruckCommand = CreateTruckCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = truckCommandService.handle(createTruckCommand);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                TruckResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }
}
