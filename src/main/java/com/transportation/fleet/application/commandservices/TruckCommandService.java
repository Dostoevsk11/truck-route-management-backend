package com.transportation.fleet.application.commandservices;

import com.transportation.fleet.domain.model.aggregates.Truck;
import com.transportation.fleet.domain.model.commands.trucks.CreateTruckCommand;
import com.transportation.fleet.domain.model.commands.trucks.DeactivateTruckCommand;
import com.transportation.fleet.domain.model.commands.trucks.UpdateTruckCommand;
import com.transportation.fleet.domain.model.commands.trucks.UpdateTruckPlateCommand;
import com.transportation.shared.application.result.ApplicationError;
import com.transportation.shared.application.result.Result;

public interface TruckCommandService {

    Result<Truck, ApplicationError> handle(CreateTruckCommand command);
    Result<Truck, ApplicationError> handle(UpdateTruckCommand command);
    Result<Truck, ApplicationError> handle(UpdateTruckPlateCommand command);
    Result<Truck,ApplicationError> handle(DeactivateTruckCommand command);
}
