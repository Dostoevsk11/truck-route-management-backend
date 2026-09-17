package com.transportation.fleet.domain.model.commands.trucks;

public record UpdateTruckPlateCommand(
        Long id,
        String plate
) {
}
