package com.transportation.fleet.infrastructure.persistence.jpa.entities;

import com.transportation.fleet.domain.model.emuns.TruckStatus;
import com.transportation.fleet.domain.model.valueobjects.truck.*;
import com.transportation.fleet.infrastructure.persistence.jpa.converters.truck.*;
import com.transportation.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "truck")
public class TruckPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Convert(converter = AxlesPersistenceConverter.class)
    @Column(name = "axles", nullable = false)
    private AxlesVo axles;

    @Convert(converter = BrandPersistenceConverter.class)
    @Column(name = "brand", nullable = false)
    private BrandVo brand;

    @Convert(converter = InspectionPersistenceConverter.class)
    @Column(name = "inspection", nullable = false)
    private InspectionVo inspection;

    @Convert(converter = LoadCapacityPersistenceConverter.class)
    @Column(name = "load_capacity", nullable = false)
    private LoadCapacityVo loadCapacity;

    @Convert(converter = ModelPersistenceConverter.class)
    @Column(name = "model", nullable = false)
    private ModelVo model;

    @Convert(converter = PlatePersistenceConverter.class)
    @Column(name = "plate", nullable = false)
    private PlateVo plate;

    @Convert(converter = SoatPersistenceConverter.class)
    @Column(name = "soat", nullable = false)
    private SoatVo soat;

    @Convert(converter = TypePersistenceConverter.class)
    @Column(name = "type", nullable = false)
    private TypeVo type;

    @Convert(converter = YearPersistenceConverter.class)
    @Column(name = "year", nullable = false)
    private YearVo year;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TruckStatus status;
}
