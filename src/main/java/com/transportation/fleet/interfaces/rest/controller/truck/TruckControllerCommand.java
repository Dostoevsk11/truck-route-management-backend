package com.transportation.fleet.interfaces.rest.controller.truck;

import com.transportation.fleet.application.commandservices.TruckCommandService;
import com.transportation.fleet.domain.model.commands.trucks.DeactivateTruckCommand;
import com.transportation.fleet.domain.model.commands.trucks.UpdateTruckCommand;
import com.transportation.fleet.domain.model.commands.trucks.UpdateTruckPlateCommand;
import com.transportation.fleet.interfaces.rest.resources.CreateTruckResource;
import com.transportation.fleet.interfaces.rest.resources.TruckResource;
import com.transportation.fleet.interfaces.rest.transform.CreateTruckCommandFromResourceAssembler;
import com.transportation.fleet.interfaces.rest.transform.TruckResourceFromEntityAssembler;
import com.transportation.shared.interfaces.rest.resources.ErrorResource;
import com.transportation.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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


@RestController
@RequestMapping(value = "/api/v1/trucks",
                produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Trucks - Commands",
        description = "Commands management endpoints")
public class TruckControllerCommand {
    private final TruckCommandService truckCommandService;

    public TruckControllerCommand(TruckCommandService truckCommandService) {
        this.truckCommandService = truckCommandService;
    }


    @PostMapping
    @Operation(
            summary = "create truck",
            description = "Creates a new truck in the system")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Truck created successfully",
                    content = @Content(
                            schema = @Schema(implementation = TruckResource.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid truck data",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResource.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Truck plate already exists",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResource.class)
                    )
            )
    })
    public ResponseEntity<?> createTruck(
            @Valid @RequestBody CreateTruckResource resource) {
        var createTruckCommand = CreateTruckCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = truckCommandService.handle(createTruckCommand);
        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                TruckResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/by-id/{truckId}")
    @Operation(
            summary = "Update truck by ID",
            description = "Updates an existing truck identified by its unique ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200",
                    description = "Truck updated successfully",
                    content = @Content(
                            schema = @Schema(
                                    implementation = TruckResource.class))),
            @ApiResponse(responseCode = "404",
                    description = "Truck not Found",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResource.class
                            )
                    )),
            @ApiResponse(responseCode = "400",
                    description = "Invalid truck data",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResource.class))),
            @ApiResponse(responseCode = "409",
                    description = "Truck plate already exists",
                    content = @Content(
                            schema = @Schema(
                                    implementation = ErrorResource.class)))
    })
    public ResponseEntity<?> updateTruck(
            @Parameter(
                    description = "The unique ID of the truck to be updated",
                    example = "1",
                    required = true)
            @PathVariable Long truckId,
            @Valid @RequestBody TruckResource resource)
    {
        var command = new UpdateTruckCommand(
                truckId,
                resource.plate(),
                resource.brand(),
                resource.model(),
                resource.year(),
                resource.type(),
                resource.loadCapacity(),
                resource.axles(),
                resource.truckStatus(),
                resource.inspection(),
                resource.soat());

        var result = truckCommandService.handle(command);


        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                TruckResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    //Update Plate
    @PatchMapping("/{truckId}/plate/{truckPlate}")
    @Operation(
            summary = "Update truck plate",
            description = "Updates the license plate of an existing truck identified by its unique ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Truck plate updated successfully",
                    content = @Content(
                            schema = @Schema(implementation = TruckResource.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Truck not Found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResource.class
                            )
                    )),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid truck data",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResource.class))),
            @ApiResponse(
                    responseCode = "409",
                    description = "Truck plate already exists",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResource.class)))
    })
    public ResponseEntity<?> updateTruckPlate(
            @Parameter(
                    description = "The unique ID of the truck to be updated",
                    example = "1",
                    required = true)
            @PathVariable Long truckId,
            @Parameter(
                    description = "The new license plate of the truck",
                    example = "ABC-123",
                    required = true)
            @PathVariable String truckPlate)
    {
        var command = new UpdateTruckPlateCommand(truckId, truckPlate);
        var result = truckCommandService.handle(command);


        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                TruckResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }

    //Delete Bok
    @PatchMapping("/truckId/{truckId}")
    @Operation(
            summary = "Deactivate truck",
            description = "Deactivates an existing truck identified by its unique ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Truck deactivated successfully",
                    content = @Content(
                            schema = @Schema(implementation = TruckResource.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Truck not found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResource.class)
                    )
            )
    })
    public ResponseEntity<?> deactivateTruck(
            @Parameter(
                    description = "The unique ID of the truck to be deactivated",
                    example = "1",
                    required = true)
            @PathVariable Long truckId)
    {
        var command = new DeactivateTruckCommand(truckId);
        var result = truckCommandService.handle(command);


        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                TruckResourceFromEntityAssembler::toResourceFromEntity,
                HttpStatus.OK
        );
    }
}
