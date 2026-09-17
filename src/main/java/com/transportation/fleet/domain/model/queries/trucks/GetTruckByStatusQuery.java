package com.transportation.fleet.domain.model.queries.trucks;

import com.transportation.fleet.domain.model.emuns.TruckStatus;

public record GetTruckByStatusQuery(TruckStatus status) {
}
