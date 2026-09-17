package com.transportation.fleet.application.internal.commandservices;

import com.transportation.fleet.application.commandservices.DriverCommandService;
import com.transportation.fleet.domain.model.aggregates.Driver;
import com.transportation.fleet.domain.model.commands.drivers.CreateDriverCommand;
import com.transportation.fleet.domain.model.commands.drivers.DeactivateDriverCommand;
import com.transportation.fleet.domain.model.commands.drivers.UpdateDriverCommand;
import com.transportation.fleet.domain.model.commands.drivers.UpdateDriverLicenseCommand;
import com.transportation.fleet.domain.model.valueobjects.drivers.LicenseVo;
import com.transportation.fleet.domain.repositories.DriverRepository;
import com.transportation.shared.application.result.ApplicationError;
import com.transportation.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class DriverCommandServiceImpl implements DriverCommandService {

    private final DriverRepository driverRepository;

    public DriverCommandServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public Result<Driver, ApplicationError> handle(CreateDriverCommand command) {
        try {
            var license = new LicenseVo(command.license());
            if (driverRepository.existsByLicense(license)) {
                return Result.failure(ApplicationError.conflict(
                        "Driver",
                        "This '%s' license already exists".formatted(command.license())
                ));
            }
            var driver = new Driver(command);
            var savedDriver = driverRepository.save(driver);
            return Result.success(savedDriver);
        }
        catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError(
                    "Driver",
                    e.getMessage()
            ));
        }
        catch (Exception e) {
            return Result.failure(ApplicationError.unexpected(
                    "Driver Creation",
                    e.getMessage()
            ));
        }
    }

    @Override
    public Result<Driver, ApplicationError> handle(UpdateDriverCommand command) {
        try {
            var driverOptional = driverRepository.findById(command.id());
            if(driverOptional.isEmpty()) {
                return Result.failure(
                        ApplicationError.notFound(
                        "Driver Update",
                        "Driver with id '%s' does not exist".formatted(command.id())
                ));
            }
            var driver = driverOptional.get();

            driver.update(
                    command.givenNames(),
                    command.paternalSurname(),
                    command.maternalSurname(),
                    command.dni(),
                    command.license(),
                    command.status()
            );
            var updateDriver = driverRepository.save(driver);
            return Result.success(updateDriver);
        }
        catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError(
                    "Driver Update",
                    e.getMessage()
            ));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected(
                    "Driver Update",
                    e.getMessage()
            ));
        }
    }

    @Override
    public Result<Driver, ApplicationError> handle(UpdateDriverLicenseCommand command) {
        try {
            var driverOptional= driverRepository.findById(command.id());
            if(driverOptional.isEmpty()) {
                return Result.failure(
                        ApplicationError.notFound(
                                "Driver",
                                "Driver with id '%s' does not exist".formatted(command.id())
                        ));
            }
            var driver = driverOptional.get();

            var newLicense = new LicenseVo(command.license());


            var driverWhitLicense = driverRepository.findByLicense(newLicense);

            if(driverWhitLicense.isPresent() &&
                    !driverWhitLicense
                            .get()
                            .getDriverId()
                            .equals(
                                    driver.getDriverId())){
                return Result.failure(ApplicationError.conflict(
                        "Driver",
                        "License already exists"));
            }
            driver.updateLicense(newLicense);

            var updateDriver = driverRepository.save(driver);

            return Result.success(updateDriver);
        }
        catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError(
                    "Driver Update",
                    e.getMessage()
            ));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected(
                    "Driver Update",
                    e.getMessage()
            ));
        }
    }

    @Override
    public Result<Driver, ApplicationError> handle(DeactivateDriverCommand command) {
        try {
            var driverOptional = driverRepository.findById(command.id());
            if(driverOptional.isEmpty()) {
                return Result.failure(
                        ApplicationError.notFound(
                                "Driver",
                                "Driver with id '%s' does not exist".formatted(command.id())
                        ));
            }
            var driver = driverOptional.get();
            driver.deactivate();
            var deactivateDriver = driverRepository.save(driver);

            return Result.success(deactivateDriver);

        }catch (IllegalArgumentException e){
            return Result.failure(ApplicationError.validationError(
                    "Driver Deactivation",
                    e.getMessage()
            ));
        } catch (Exception e) {
            return Result.failure(ApplicationError.unexpected(
                    "Driver Deactivation",
                    e.getMessage()
            ));
        }
    }
}
