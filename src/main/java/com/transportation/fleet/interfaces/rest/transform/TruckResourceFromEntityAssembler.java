package com.transportation.fleet.interfaces.rest.transform;

import com.transportation.fleet.domain.model.aggregates.Truck;
import com.transportation.fleet.interfaces.rest.resources.TruckResource;

public class TruckResourceFromEntityAssembler {
    public static TruckResource toResourceFromEntity(Truck entity){
        return new TruckResource(entity.getTruckId(),
                entity.getPlateVo().value(),
                entity.getBrandVo().value(),
                entity.getModelVo().value(),
                entity.getYearVo().value(),
                entity.getTypeVo().value(),
                entity.getLoadCapacityVo().value(),
                entity.getAxlesVo().value(),
                entity.getTruckStatus().name(),
                entity.getInspectionVo().value(),
                entity.getSoatVo().value());
    }
}
