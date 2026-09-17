package com.transportation.fleet.application.internal.commandservices;

import com.transportation.fleet.application.commandservices.TruckCommandService;
import com.transportation.fleet.domain.model.aggregates.Truck;
import com.transportation.fleet.domain.model.commands.trucks.CreateTruckCommand;
import com.transportation.fleet.domain.model.commands.trucks.DeactivateTruckCommand;
import com.transportation.fleet.domain.model.commands.trucks.UpdateTruckCommand;
import com.transportation.fleet.domain.model.commands.trucks.UpdateTruckPlateCommand;
import com.transportation.fleet.domain.model.valueobjects.truck.PlateVo;
import com.transportation.fleet.domain.repositories.TruckRepository;
import com.transportation.shared.application.result.ApplicationError;
import com.transportation.shared.application.result.Result;
import org.springframework.stereotype.Service;

@Service
public class TruckCommandServiceImpl implements TruckCommandService {

    private final TruckRepository truckRepository;

    public TruckCommandServiceImpl(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @Override
    public Result<Truck, ApplicationError> handle(CreateTruckCommand command) {
        try {
            var plate = new PlateVo(command.plate());
            if (truckRepository.existsByPlate(plate)) {
                return Result.failure(ApplicationError.conflict(
                        "Truck",
                        "This '%s' Plate already exists".formatted(command.plate())
                ));
            }
            var truck = new Truck(command);
            var savedTruck = truckRepository.save(truck);
            return Result.success(savedTruck);
        }
        catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError(
                    "Truck",
                    e.getMessage()
            ));
        }
        catch (Exception e) {
            return Result.failure(ApplicationError.unexpected(
                    "Truck Creation",
                    e.getMessage()
            ));
        }
    }
    @Override
    public Result<Truck, ApplicationError> handle(UpdateTruckCommand command) {
        try {
         var truckOptional = truckRepository.findById(command.id());
         if(truckOptional.isEmpty()) {
             return Result.failure(ApplicationError.validationError(
                     "Truck",
                     "Truck not found"
             ));
         }
         var truck = truckOptional.get();

         truck.update(command.plate(),
                      command.brand(),
                      command.model(),
                      command.year(),
                      command.type(),
                      command.capacity(),
                      command.axles(),
                      command.status(),
                      command.inspection(),
                      command.soat()
         );
         var updateTruck = truckRepository.save(truck);
         return Result.success(updateTruck);

        }catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError(
                    "Truck",
                    e.getMessage()
            ));
        }catch (Exception e) {
            return Result.failure(ApplicationError.unexpected(
                    "Truck Update",
                    e.getMessage()
            ));
        }
    }
    @Override
    public Result<Truck, ApplicationError> handle(UpdateTruckPlateCommand command) {
        try {
            var truckOptional = truckRepository.findById(command.id());
            if(truckOptional.isEmpty()) {
                return Result.failure(ApplicationError.validationError(
                        "Truck",
                        "Truck not found"
                ));
            }
            var truck = truckOptional.get();

            var newPlate = new PlateVo(command.plate());

            var truckWithPlate  = truckRepository.findByPlate(newPlate);

            if(truckWithPlate.isPresent() && !truckWithPlate
                                                    .get()
                                                    .getTruckId()
                                                    .equals(
                                                            truck.getTruckId())) {
                return Result.failure(ApplicationError.validationError(
                        "Truck",
                        "Plate already exists"
                ));
            }
            truck.updatePlate(newPlate);

            var updateTruck = truckRepository.save(truck);

            return Result.success(updateTruck);

        }catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError(
                    "Truck",
                    e.getMessage()
            ));
        }catch (Exception e) {
            return Result.failure(ApplicationError.unexpected(
                    "Truck Update",
                    e.getMessage()
            ));
        }
    }
    @Override
    public Result<Truck, ApplicationError> handle(DeactivateTruckCommand command) {
        try {
            var truckOptional = truckRepository.findById(command.id());
            if(truckOptional.isEmpty()) {
                return Result.failure(ApplicationError.validationError(
                        "Truck",
                        "Truck not found"
                ));
            }
            var truck = truckOptional.get();

            truck.deactivate();

            var deactivateTruck = truckRepository.save(truck);

            return Result.success(deactivateTruck);

        }catch (IllegalArgumentException e) {
            return Result.failure(ApplicationError.validationError(
                    "Truck",
                    e.getMessage()
            ));
        }catch (Exception e) {
            return Result.failure(ApplicationError.unexpected(
                    "Truck Update",
                    e.getMessage()
            ));
        }
    }
}
