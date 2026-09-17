package com.transportation.fleet.domain.repositories;

import com.transportation.fleet.domain.model.aggregates.Driver;
import com.transportation.fleet.domain.model.emuns.DriverStatus;
import com.transportation.fleet.domain.model.valueobjects.drivers.LicenseVo;

import java.util.List;
import java.util.Optional;

public interface DriverRepository {
    List<Driver> findAll();
    Optional<Driver> findById(Long id);
    Optional<Driver> findByLicense(LicenseVo license);
    List<Driver> findByStatus(DriverStatus status);
    Driver save(Driver driver);
    boolean existsByLicense(LicenseVo license);

}
