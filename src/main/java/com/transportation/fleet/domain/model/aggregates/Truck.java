package com.transportation.fleet.domain.model.aggregates;

import com.transportation.fleet.domain.model.commands.trucks.CreateTruckCommand;
import com.transportation.fleet.domain.model.emuns.TruckStatus;
import com.transportation.fleet.domain.model.valueobjects.truck.*;
import com.transportation.fleet.domain.model.valueobjects.truck.InspectionVo;
import com.transportation.fleet.domain.model.valueobjects.truck.SoatVo;
import com.transportation.shared.domain.models.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Objects;

@Getter
public class Truck extends AbstractDomainAggregateRoot<Truck> {


    private Long truckId;

    private PlateVo plateVo;
    private BrandVo brandVo;
    private ModelVo modelVo;
    private YearVo yearVo;
    private TypeVo typeVo;
    private LoadCapacityVo loadCapacityVo;
    private AxlesVo axlesVo;
    private TruckStatus truckStatus;
    private InspectionVo inspectionVo;
    private SoatVo soatVo;

    public Truck(Long truckId,
                 PlateVo plateVo,
                 BrandVo brandVo,
                 ModelVo modelVo,
                 YearVo yearVo,
                 TypeVo typeVo,
                 LoadCapacityVo loadCapacityVo,
                 AxlesVo axlesVo,
                 TruckStatus truckStatus,
                 InspectionVo inspectionVo,
                 SoatVo soatVo) {
        this.truckId = truckId;
        this.plateVo = Objects.requireNonNull(plateVo,"Not be Null");
        this.brandVo = Objects.requireNonNull(brandVo,"Not be Null");
        this.modelVo = Objects.requireNonNull(modelVo,"Not be Null");
        this.yearVo = Objects.requireNonNull(yearVo,"Not be Null");
        this.typeVo = Objects.requireNonNull(typeVo,"Not be Null");
        this.loadCapacityVo = Objects.requireNonNull(loadCapacityVo,"Not be Null");
        this.axlesVo = Objects.requireNonNull(axlesVo,"Not be Null");
        this.truckStatus = Objects.requireNonNull(truckStatus,"Not be Null");
        this.inspectionVo = Objects.requireNonNull(inspectionVo,"Not be Null");
        this.soatVo = Objects.requireNonNull(soatVo,"Not be Null");
    }

    public Truck(PlateVo plateVo,
                 BrandVo brandVo,
                 ModelVo modelVo,
                 YearVo yearVo,
                 TypeVo typeVo,
                 LoadCapacityVo loadCapacityVo,
                 AxlesVo axlesVo,
                 TruckStatus truckStatus,
                 InspectionVo inspectionVo,
                 SoatVo soatVo){
        this(null,
                plateVo,
                brandVo,
                modelVo,
                yearVo,
                typeVo,
                loadCapacityVo,
                axlesVo,
                truckStatus,
                inspectionVo,
                soatVo);
        validateDocuments();
    }
    public Truck(String plate,
                 String brand,
                 String model,
                 int year,
                 String type,
                 BigDecimal capacity,
                 int axles,
                 String status,
                 LocalDate inspection,
                 LocalDate soat){
        this    (new PlateVo(plate),
                new BrandVo(brand),
                new ModelVo(model),
                new YearVo(year),
                new TypeVo(type),
                new LoadCapacityVo(capacity),
                new AxlesVo(axles),
                TruckStatus.valueOf(status.trim().toUpperCase(Locale.ROOT)),
                new InspectionVo(inspection),
                new SoatVo(soat)
        );
    }
    public Truck(CreateTruckCommand command){
        this(command.plate(),
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
    }

    public void validateDocuments(){
        if(truckStatus == TruckStatus.AVAILABLE && (soatVo.isExpired() || inspectionVo.isExpired())){
            this.truckStatus = TruckStatus.INACTIVE;
        }
    }

    public void update(String plate,
                       String brand,
                       String model,
                       int year,
                       String type,
                       BigDecimal capacity,
                       int axles,
                       String status,
                       LocalDate inspection,
                       LocalDate soat){
        this.plateVo = new PlateVo(plate);
        this.brandVo = new BrandVo(brand);
        this.modelVo = new ModelVo(model);
        this.yearVo = new YearVo(year);
        this.typeVo = new TypeVo(type);
        this.loadCapacityVo = new LoadCapacityVo(capacity);
        this.axlesVo = new AxlesVo(axles);
        this.truckStatus = TruckStatus.valueOf(status.trim().toUpperCase(Locale.ROOT));
        this.inspectionVo = new InspectionVo(inspection);
        this.soatVo = new SoatVo(soat);
    }
    public void updatePlate(PlateVo plate){
        this.plateVo = plate;
    }

    public void deactivate(){
        this.truckStatus = TruckStatus.INACTIVE;
    }
}
