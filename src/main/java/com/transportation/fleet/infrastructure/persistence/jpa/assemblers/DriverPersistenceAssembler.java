package com.transportation.fleet.infrastructure.persistence.jpa.assemblers;

import com.transportation.fleet.domain.model.aggregates.Driver;
import com.transportation.fleet.domain.model.valueobjects.drivers.PersonName;
import com.transportation.fleet.infrastructure.persistence.jpa.embeddables.PersonNamePersistenceEmbeddable;
import com.transportation.fleet.infrastructure.persistence.jpa.entities.DriverPersistenceEntity;

public class DriverPersistenceAssembler {
    private DriverPersistenceAssembler() {}

    public static PersonName toDomainFromPersistence(PersonNamePersistenceEmbeddable personName){
        return new PersonName(
                personName.getGivenNames(),
                personName.getPaternalSurname(),
                personName.getMaternalSurname()
        );
    }

    public static Driver toDomainFromPersistence(DriverPersistenceEntity entity){
        return new Driver(
                entity.getId(),
                entity.getProfileId(),
                toDomainFromPersistence(entity.getPersonName()),
                entity.getDni(),
                entity.getLicense(),
                entity.getStatus()
        );
    }

    public static PersonNamePersistenceEmbeddable toPersistenceFromDomain(PersonName personName){
        return new PersonNamePersistenceEmbeddable(
                personName.givenNames(),
                personName.paternalSurname(),
                personName.maternalSurname()
        );
    }

    public static DriverPersistenceEntity toPersistenceFromDomain(Driver driver){
        var entity = new DriverPersistenceEntity();
        entity.setId(driver.getDriverId());
        entity.setProfileId(driver.getProfileId());
        entity.setPersonName(toPersistenceFromDomain(driver.getPersonName()));
        entity.setDni(driver.getDniVo());
        entity.setLicense(driver.getLicense());
        entity.setStatus(driver.getDriverStatus());
        return entity;
    }
}
