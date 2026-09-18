package com.transportation.fleet.interfaces.rest.controller.truck;

import com.transportation.fleet.application.queryservices.TruckQueryService;
import com.transportation.fleet.domain.model.emuns.TruckStatus;
import com.transportation.fleet.domain.model.queries.trucks.GetAllTrucksQuery;
import com.transportation.fleet.domain.model.queries.trucks.GetTruckByStatusQuery;
import com.transportation.fleet.domain.model.queries.trucks.GetlTruckByIdQuery;
import com.transportation.fleet.domain.model.queries.trucks.GetlTruckByPlateQuery;
import com.transportation.fleet.interfaces.rest.resources.TruckResource;
import com.transportation.fleet.interfaces.rest.transform.TruckResourceFromEntityAssembler;
import com.transportation.shared.application.result.ApplicationError;
import com.transportation.shared.interfaces.rest.resources.ErrorResource;
import com.transportation.shared.interfaces.rest.transform.ErrorResponseAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping(value = "/api/v1/trucks",
                produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Trucks - Queries",
        description = "Queries management endpoints")
public class TruckControllerGet {
    private final TruckQueryService truckQueryService;

    public TruckControllerGet(TruckQueryService truckQueryService) {
        this.truckQueryService = truckQueryService;
    }

    //Get All
    @GetMapping
    @Operation(
            summary = "Get all trucks",
            description = "Retrieves all registered trucks")
    @ApiResponse(
            responseCode = "200",
            description = "Trucks retrieved successfully",
            content = @Content(
                    array = @ArraySchema(
                            schema = @Schema(
                                    implementation = TruckResource.class))))
    public ResponseEntity<List<TruckResource>> getAllTrucks()
    {
        var trucks = truckQueryService.handle(new GetAllTrucksQuery());
        var truckResources = trucks.stream()
                .map(TruckResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(truckResources);
    }

    //GetById
    @GetMapping("/{truckId}")
    @Operation(
            summary = "Get truck by ID",
            description = "Retrieves a truck by its unique identifier")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Truck retrieved successfully",
                    content = @Content(
                            schema = @Schema(implementation = TruckResource.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Truck not found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResource.class)
                    )),
            @ApiResponse(
                    responseCode = "409",
                    description = "Truck cannot be deactivated due to its current status",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResource.class)
                    ))
    })
    public ResponseEntity<?> getTruckById(
            @Parameter(
                    description = "Unique identifier of the truck",
                    example = "1",
                    required = true)
            @PathVariable Long truckId)

    {
    var query = new GetlTruckByIdQuery(truckId);
    var truck = truckQueryService.handle(query);
    if(truck.isEmpty()){
        var error = ApplicationError.notFound(
                "Truck",
                truckId.toString());
        return ErrorResponseAssembler.toErrorResponseFromApplicationError(error);
    }
    var truckEntity = truck.get();
    var truckResource = TruckResourceFromEntityAssembler.toResourceFromEntity(truckEntity);
    return ResponseEntity.ok(truckResource);
    }


    //GetByPlate
    @GetMapping("/by-plate/{truckPlate}")
    @Operation(
            summary = "Get truck by plate",
            description = "Retrieves a truck by its unique license plate")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Truck retrieved successfully",
                    content = @Content(
                            schema = @Schema(implementation = TruckResource.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid truck plate",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResource.class)
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
    public ResponseEntity<?> getTruckByPlate(
            @Parameter(
                    description = "Unique identifier of the truck",
                    example = "1",
                    required = true)
            @PathVariable String truckPlate)

    {
        var query = new GetlTruckByPlateQuery(truckPlate);
        var truck = truckQueryService.handle(query);

        if(truck.isEmpty()){
            var error = ApplicationError.notFound(
                    "Truck",
                    truckPlate);

            return ErrorResponseAssembler.toErrorResponseFromApplicationError(error);
        }
        var truckEntity = truck.get();
        var truckResource = TruckResourceFromEntityAssembler.toResourceFromEntity(truckEntity);

        return ResponseEntity.ok(truckResource);
    }

    //GetByStatus
    @GetMapping("/by-status/{truckStatus}")
    @Operation(
            summary = "Get truck by status",
            description = "Retrieves all trucks matching the specified status")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Truck retrieved successfully",
                    content = @Content(
                            schema = @Schema(implementation = TruckResource.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Truck not found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResource.class)
                    ))
    })
    public ResponseEntity<List<TruckResource>> getTruckByStatus(
            @Parameter(
                    description = "Unique identifier of the truck",
                    example = "1",
                    required = true)
            @PathVariable String truckStatus)

    {
        var status = TruckStatus.valueOf(truckStatus.toUpperCase(Locale.ROOT));
        var query = new GetTruckByStatusQuery(status);

        var resources = truckQueryService.handle(query)
                .stream()
                .map(TruckResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(resources);
    }

}
