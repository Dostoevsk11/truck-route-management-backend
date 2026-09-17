package com.transportation.fleet.infrastructure.persistence.jpa.repositories;

import com.transportation.fleet.domain.model.emuns.DriverStatus;
import com.transportation.fleet.domain.model.valueobjects.drivers.LicenseVo;
import com.transportation.fleet.infrastructure.persistence.jpa.entities.DriverPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DriverPersistenceRepository extends JpaRepository<DriverPersistenceEntity, Long> {
    Optional<DriverPersistenceEntity> findByLicense(LicenseVo license);
    List<DriverPersistenceEntity> findByStatus(DriverStatus status);
    boolean existsByLicense(LicenseVo license);

}
