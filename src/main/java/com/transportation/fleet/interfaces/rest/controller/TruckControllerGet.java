package com.transportation.fleet.interfaces.rest.controller;

import com.transportation.fleet.application.queryservices.TruckQueryService;
import com.transportation.fleet.domain.model.queries.trucks.GetAllTrucksQuery;
import com.transportation.fleet.interfaces.rest.resources.TruckResource;
import com.transportation.fleet.interfaces.rest.transform.TruckResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

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

    @GetMapping
    @Operation(summary = "Get all trucks",
               description = "Returns a list of all trucks")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                        description = "Successful operation"),
            @ApiResponse(responseCode = "404",
                    description = "Trucks not found")
    })
    public ResponseEntity<List<TruckResource>> getAllTrucks() {
        var trucks = truckQueryService.handle(new GetAllTrucksQuery());
        //se sobre entiende
        if(trucks.isEmpty()){
            return ResponseEntity.ok(Collections.emptyList());
        }
        //
        var truckResources = trucks.stream()
                .map(TruckResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(truckResources);
    }
}
