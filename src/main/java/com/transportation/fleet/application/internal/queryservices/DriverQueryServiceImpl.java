package com.transportation.fleet.application.internal.queryservices;

import com.transportation.fleet.application.queryservices.DriverQueryService;
import com.transportation.fleet.domain.model.aggregates.Driver;
import com.transportation.fleet.domain.model.queries.drivers.GetAllDriversQuery;
import com.transportation.fleet.domain.model.queries.drivers.GetDriveByIdQuery;
import com.transportation.fleet.domain.model.queries.drivers.GetDriveByLicenseQuery;
import com.transportation.fleet.domain.model.queries.drivers.GetDriveByStatusQuery;
import com.transportation.fleet.domain.model.valueobjects.drivers.LicenseVo;
import com.transportation.fleet.domain.repositories.DriverRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Locale;
import java.util.Optional;


@Service
public class DriverQueryServiceImpl implements DriverQueryService {

    private final DriverRepository driverRepository;

    public DriverQueryServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public List<Driver> handle(GetAllDriversQuery query) {
        return driverRepository.findAll();
    }

    @Override
    public Optional<Driver> handle(GetDriveByIdQuery query) {
        return driverRepository.findById(query.id());
    }

    @Override
    public Optional<Driver> handle(GetDriveByLicenseQuery query) {
        return driverRepository.findByLicense(new LicenseVo(query.license()));
    }

    @Override
    public List<Driver> handle(GetDriveByStatusQuery query) {
        return driverRepository.findByStatus(query.status());
    }
}
