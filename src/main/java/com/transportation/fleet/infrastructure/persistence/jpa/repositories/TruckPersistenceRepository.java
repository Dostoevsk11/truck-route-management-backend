package com.transportation.fleet.infrastructure.persistence.jpa.repositories;

import com.transportation.fleet.domain.model.emuns.TruckStatus;
import com.transportation.fleet.domain.model.valueobjects.truck.PlateVo;
import com.transportation.fleet.infrastructure.persistence.jpa.entities.TruckPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TruckPersistenceRepository extends JpaRepository<TruckPersistenceEntity, Long> {
    Optional<TruckPersistenceEntity> findByPlate(PlateVo plate);
    List<TruckPersistenceEntity> findByStatus(TruckStatus status);
    boolean existsByPlate(PlateVo plate);

}
