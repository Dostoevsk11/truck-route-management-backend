package com.transportation.fleet.domain.model.commands.drivers;

public record CreateDriverCommand(Long profileId,
                                  String givenNames,
                                  String paternalSurname,
                                  String maternalSurname,
                                  String dni,
                                  String license,
                                  String status) {
}
