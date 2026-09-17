package com.transportation.fleet.domain.model.commands.drivers;

public record UpdateDriverCommand(Long id,
                                  String givenNames,
                                  String paternalSurname,
                                  String maternalSurname,
                                  String dni,
                                  String license,
                                  String status) {
}
