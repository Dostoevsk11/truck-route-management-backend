package com.transportation.fleet.domain.repositories;

import com.transportation.fleet.domain.model.aggregates.Truck;
import com.transportation.fleet.domain.model.emuns.TruckStatus;
import com.transportation.fleet.domain.model.valueobjects.truck.PlateVo;

import java.util.List;
import java.util.Optional;

public interface TruckRepository {
    List<Truck> findAll();
    Optional<Truck> findById(Long id);
    Optional<Truck> findByPlate(PlateVo plate);
    List<Truck> findByStatus(TruckStatus status);
    Truck save(Truck truck);
    boolean existsByPlate(PlateVo plate);
}
