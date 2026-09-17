package com.transportation.fleet.infrastructure.persistence.jpa.assemblers;

import com.transportation.fleet.domain.model.aggregates.Truck;
import com.transportation.fleet.infrastructure.persistence.jpa.entities.TruckPersistenceEntity;

public class TruckPersistenceAssembler {
    private TruckPersistenceAssembler() {}


    public static Truck toDomainFromPersistence(TruckPersistenceEntity entity){
        return new Truck(
                entity.getId(),
                entity.getPlate(),
                entity.getBrand(),
                entity.getModel(),
                entity.getYear(),
                entity.getType(),
                entity.getLoadCapacity(),
                entity.getAxles(),
                entity.getStatus(),
                entity.getInspection(),
                entity.getSoat()
        );
    }



    public static TruckPersistenceEntity toPersistenceFromDomain(Truck truck){
        var entity = new TruckPersistenceEntity();
        entity.setId(truck.getTruckId());
        entity.setPlate(truck.getPlateVo());
        entity.setBrand(truck.getBrandVo());
        entity.setModel(truck.getModelVo());
        entity.setYear(truck.getYearVo());
        entity.setType(truck.getTypeVo());
        entity.setLoadCapacity(truck.getLoadCapacityVo());
        entity.setAxles(truck.getAxlesVo());
        entity.setStatus(truck.getTruckStatus());
        entity.setInspection(truck.getInspectionVo());
        entity.setSoat(truck.getSoatVo());
        return entity;
    }
}
