package com.transportation.fleet.application.commandservices;

import com.transportation.fleet.domain.model.aggregates.Driver;
import com.transportation.fleet.domain.model.commands.drivers.CreateDriverCommand;
import com.transportation.fleet.domain.model.commands.drivers.DeactivateDriverCommand;
import com.transportation.fleet.domain.model.commands.drivers.UpdateDriverCommand;
import com.transportation.fleet.domain.model.commands.drivers.UpdateDriverLicenseCommand;
import com.transportation.shared.application.result.ApplicationError;
import com.transportation.shared.application.result.Result;

public interface DriverCommandService {

    Result<Driver, ApplicationError> handle(CreateDriverCommand command);
    Result<Driver, ApplicationError> handle(UpdateDriverCommand command);
    Result<Driver, ApplicationError> handle(UpdateDriverLicenseCommand command);
    Result<Driver,ApplicationError> handle(DeactivateDriverCommand command);
}
