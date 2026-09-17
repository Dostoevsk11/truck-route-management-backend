package com.transportation.fleet.application.queryservices;

import com.transportation.fleet.domain.model.aggregates.Truck;
import com.transportation.fleet.domain.model.queries.trucks.GetAllTrucksQuery;
import com.transportation.fleet.domain.model.queries.trucks.GetTruckByStatusQuery;
import com.transportation.fleet.domain.model.queries.trucks.GetlTruckByIdQuery;
import com.transportation.fleet.domain.model.queries.trucks.GetlTruckByPlateQuery;

import java.util.List;
import java.util.Optional;

public interface TruckQueryService {

    List<Truck> handle(GetAllTrucksQuery query);
    Optional<Truck> handle(GetlTruckByIdQuery query);
    Optional<Truck> handle(GetlTruckByPlateQuery query);
    List<Truck> handle(GetTruckByStatusQuery query);
}
