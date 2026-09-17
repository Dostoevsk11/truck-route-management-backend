package com.transportation.fleet.infrastructure.persistence.jpa.entities;

import com.transportation.fleet.domain.model.emuns.DriverStatus;
import com.transportation.fleet.domain.model.valueobjects.drivers.DniVo;
import com.transportation.fleet.domain.model.valueobjects.drivers.LicenseVo;
import com.transportation.fleet.infrastructure.persistence.jpa.converters.drivers.DniPersistenceConverter;
import com.transportation.fleet.infrastructure.persistence.jpa.converters.drivers.LicensePersistenceConverter;
import com.transportation.fleet.infrastructure.persistence.jpa.embeddables.PersonNamePersistenceEmbeddable;
import com.transportation.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "drivers")
public class DriverPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Column(name = "profile_id",nullable = false,unique = true)
    private Long profileId;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "givenNames",
                column = @Column(name = "driver_given_names",nullable = false)),
        @AttributeOverride(name = "paternalSurname",
                column = @Column(name = "driver_paternal_surname",nullable = false)),
        @AttributeOverride(name = "maternalSurname",
                column = @Column(name = "driver_maternal_surname",nullable = false))
    })
    private PersonNamePersistenceEmbeddable personName;

    @Convert(converter = DniPersistenceConverter.class)
    @Column(name = "driver_dni",unique = true,nullable = false)
    private DniVo dni;

    @Convert(converter = LicensePersistenceConverter.class)
    @Column(name = "driver_license",unique = true,nullable = false)
    private LicenseVo license;

    @Enumerated(EnumType.STRING)
    @Column(name = "driver_status",nullable = false)
    private DriverStatus status;
}
