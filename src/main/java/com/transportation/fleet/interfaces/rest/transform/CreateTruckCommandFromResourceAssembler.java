package com.transportation.fleet.interfaces.rest.transform;

import com.transportation.fleet.domain.model.commands.trucks.CreateTruckCommand;
import com.transportation.fleet.interfaces.rest.resources.CreateTruckResource;

public class CreateTruckCommandFromResourceAssembler  {
    public static CreateTruckCommand toCommandFromResource(CreateTruckResource resource){
        return new CreateTruckCommand(
                resource.plate(),
                resource.brand(),
                resource.model(),
                resource.year(),
                resource.type(),
                resource.loadCapacity(),
                resource.axles(),
                resource.truckStatus(),
                resource.inspection(),
                resource.soat());
    }
}
