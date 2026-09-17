package com.transportation.fleet.domain.model.commands.drivers;

public record UpdateDriverLicenseCommand(Long id,
                                         String license) {
}
