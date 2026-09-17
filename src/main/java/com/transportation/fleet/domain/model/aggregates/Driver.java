package com.transportation.fleet.domain.model.aggregates;

import com.transportation.fleet.domain.model.commands.drivers.CreateDriverCommand;
import com.transportation.fleet.domain.model.emuns.DriverStatus;
import com.transportation.fleet.domain.model.valueobjects.drivers.DniVo;
import com.transportation.fleet.domain.model.valueobjects.drivers.LicenseVo;
import com.transportation.fleet.domain.model.valueobjects.drivers.PersonName;
import com.transportation.shared.domain.models.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;

import java.util.Locale;
import java.util.Objects;

@Getter
public class Driver extends AbstractDomainAggregateRoot<Driver> {

    private Long driverId;
    private Long profileId;

    private PersonName personName;
    private DniVo dniVo;
    private LicenseVo license;
    private DriverStatus driverStatus;

    public Driver(Long driverId,
                  Long profileId,
                  PersonName personName,
                  DniVo dniVo,
                  LicenseVo licenseVo,
                  DriverStatus driverStatus) {
        this.driverId = driverId;
        this.profileId = profileId;
        this.personName = Objects.requireNonNull(personName, "personName cannot be null");
        this.dniVo = Objects.requireNonNull(dniVo, "dniVo cannot be null");
        this.license = Objects.requireNonNull(licenseVo, "licenseVo cannot be null");
        this.driverStatus = Objects.requireNonNull(driverStatus, "driverStatus cannot be null");
    }
    public Driver(Long profileId,
                  PersonName personName,
                  DniVo dniVo,
                  LicenseVo licenseVo,
                  DriverStatus driverStatus) {
        this(null,
                profileId,
                personName,
                dniVo,
                licenseVo,
                driverStatus);
    }
    public Driver(Long profileId,
                  String givenNames,
                  String paternalSurname,
                  String maternalSurname,
                  String dni,
                  String license,
                  String status) {
        this(profileId,
                new PersonName(givenNames, paternalSurname, maternalSurname),
                new DniVo(dni),
                new LicenseVo(license),
                DriverStatus.valueOf(status.trim().toUpperCase(Locale.ROOT)));
    }
    public Driver(CreateDriverCommand command) {
        this(command.profileId(),
                command.givenNames(),
                command.paternalSurname(),
                command.maternalSurname(),
                command.dni(),
                command.license(),
                command.status()
        );
    }

    // Updates
    public void update(String givenNames,
                       String paternalSurname,
                       String maternalSurname,
                       String dni,
                       String license,
                       String status){
        this.personName = new PersonName(givenNames, paternalSurname, maternalSurname);
        this.dniVo = new DniVo(dni);
        this.license = new LicenseVo(license);
        this.driverStatus = DriverStatus.valueOf(status.toUpperCase(Locale.ROOT));
    }
    public void updateLicense(LicenseVo licenseVo) {
        this.license = licenseVo;
    }
    public void deactivate() {
        this.driverStatus = DriverStatus.INACTIVE;
    }
}
