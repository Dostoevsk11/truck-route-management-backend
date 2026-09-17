package com.transportation.fleet.application.queryservices;

import com.transportation.fleet.domain.model.aggregates.Driver;
import com.transportation.fleet.domain.model.queries.drivers.GetAllDriversQuery;
import com.transportation.fleet.domain.model.queries.drivers.GetDriveByIdQuery;
import com.transportation.fleet.domain.model.queries.drivers.GetDriveByLicenseQuery;
import com.transportation.fleet.domain.model.queries.drivers.GetDriveByStatusQuery;

import java.util.List;
import java.util.Optional;

public interface DriverQueryService {

    List<Driver> handle(GetAllDriversQuery query);
    Optional<Driver> handle(GetDriveByIdQuery query);
    Optional<Driver> handle(GetDriveByLicenseQuery query);
    List<Driver> handle(GetDriveByStatusQuery query);
}
