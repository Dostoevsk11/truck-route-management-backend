package com.transportation.fleet.infrastructure.persistence.jpa.adapters;

import com.transportation.fleet.domain.model.aggregates.Driver;
import com.transportation.fleet.domain.model.emuns.DriverStatus;
import com.transportation.fleet.domain.model.valueobjects.drivers.LicenseVo;
import com.transportation.fleet.domain.repositories.DriverRepository;
import com.transportation.fleet.infrastructure.persistence.jpa.assemblers.DriverPersistenceAssembler;
import com.transportation.fleet.infrastructure.persistence.jpa.repositories.DriverPersistenceRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DriverRepositoryImpl implements DriverRepository {

    private final DriverPersistenceRepository driverPersistenceRepository;

    public DriverRepositoryImpl(DriverPersistenceRepository driverPersistenceRepository) {
        this.driverPersistenceRepository = driverPersistenceRepository;
    }
    @Override
    public List<Driver> findAll() {
        return driverPersistenceRepository.findAll()
                .stream()
                .map(DriverPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }
    @Override
    public Optional<Driver> findById(Long id) {
        return driverPersistenceRepository.findById(id)
                .map(DriverPersistenceAssembler::toDomainFromPersistence);
    }
    @Override
    public Optional<Driver> findByLicense(LicenseVo license) {
        return driverPersistenceRepository.findByLicense(license)
                .map(DriverPersistenceAssembler::toDomainFromPersistence);
    }
    @Override
    public List<Driver> findByStatus(DriverStatus status) {
        return driverPersistenceRepository.findByStatus(status)
                .stream()
                .map(DriverPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }
    @Override
    public Driver save(Driver driver) {
        var saved = driverPersistenceRepository.save(
                DriverPersistenceAssembler.toPersistenceFromDomain(driver));
        return DriverPersistenceAssembler.toDomainFromPersistence(saved);
    }


    @Override
    public boolean existsByLicense(LicenseVo license) {
        return driverPersistenceRepository.existsByLicense(license);
    }
}
