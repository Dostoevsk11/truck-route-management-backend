package com.transportation.fleet.domain.model.queries.drivers;

import com.transportation.fleet.domain.model.emuns.DriverStatus;

public record GetDriveByStatusQuery(DriverStatus status) {
}
