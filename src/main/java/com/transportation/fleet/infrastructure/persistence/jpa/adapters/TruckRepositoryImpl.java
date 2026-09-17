package com.transportation.fleet.infrastructure.persistence.jpa.adapters;

import com.transportation.fleet.domain.model.aggregates.Truck;
import com.transportation.fleet.domain.model.emuns.TruckStatus;
import com.transportation.fleet.domain.model.valueobjects.truck.PlateVo;
import com.transportation.fleet.domain.repositories.TruckRepository;
import com.transportation.fleet.infrastructure.persistence.jpa.assemblers.TruckPersistenceAssembler;
import com.transportation.fleet.infrastructure.persistence.jpa.repositories.TruckPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TruckRepositoryImpl implements TruckRepository {

    private final TruckPersistenceRepository truckPersistenceRepository;

    public TruckRepositoryImpl(TruckPersistenceRepository truckPersistenceRepository) {
        this.truckPersistenceRepository = truckPersistenceRepository;
    }
    @Override
    public List<Truck> findAll() {
        return truckPersistenceRepository.findAll()
                .stream()
                .map(TruckPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }
    @Override
    public Optional<Truck> findById(Long id) {
        return truckPersistenceRepository.findById(id)
                .map(TruckPersistenceAssembler::toDomainFromPersistence);
    }
    @Override
    public Optional<Truck> findByPlate(PlateVo plate) {
        return truckPersistenceRepository.findByPlate(plate)
                .map(TruckPersistenceAssembler::toDomainFromPersistence);
    }
    @Override
    public List<Truck> findByStatus(TruckStatus status) {
        return truckPersistenceRepository.findByStatus(status)
                .stream()
                .map(TruckPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }
    @Override
    public Truck save(Truck truck) {
        var saved = truckPersistenceRepository.save(
                TruckPersistenceAssembler.toPersistenceFromDomain(truck));
        return TruckPersistenceAssembler.toDomainFromPersistence(saved);
    }

    @Override
    public boolean existsByPlate(PlateVo plate) {
        return truckPersistenceRepository.existsByPlate(plate);
    }
}
