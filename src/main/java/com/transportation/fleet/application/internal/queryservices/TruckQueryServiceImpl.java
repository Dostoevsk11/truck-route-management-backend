package com.transportation.fleet.application.internal.queryservices;

import com.transportation.fleet.application.queryservices.TruckQueryService;
import com.transportation.fleet.domain.model.aggregates.Truck;
import com.transportation.fleet.domain.model.queries.trucks.GetAllTrucksQuery;
import com.transportation.fleet.domain.model.queries.trucks.GetTruckByStatusQuery;
import com.transportation.fleet.domain.model.queries.trucks.GetlTruckByIdQuery;
import com.transportation.fleet.domain.model.queries.trucks.GetlTruckByPlateQuery;
import com.transportation.fleet.domain.model.valueobjects.truck.PlateVo;
import com.transportation.fleet.domain.repositories.TruckRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;


@Service
public class TruckQueryServiceImpl implements TruckQueryService {

    private final TruckRepository truckRepository;

    public TruckQueryServiceImpl(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @Override
    public List<Truck> handle(GetAllTrucksQuery query) {
        return truckRepository.findAll();
    }

    @Override
    public Optional<Truck> handle(GetlTruckByIdQuery query) {
        return truckRepository.findById(query.id());
    }

    @Override
    public Optional<Truck> handle(GetlTruckByPlateQuery query) {
        return truckRepository.findByPlate(new PlateVo(query.plate()));
    }

    @Override
    public List<Truck> handle(GetTruckByStatusQuery query) {
        return truckRepository.findByStatus(query.status());
    }
}
