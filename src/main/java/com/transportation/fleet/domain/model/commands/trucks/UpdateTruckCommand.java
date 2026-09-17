package com.transportation.fleet.domain.model.commands.trucks;

import java.math.BigDecimal;
import java.time.LocalDate;

public record UpdateTruckCommand (Long id,
                                  String plate,
                                 String brand,
                                 String model,
                                 int year,
                                 String type,
                                 BigDecimal capacity,
                                 int axles,
                                 String status,
                                 LocalDate inspection,
                                 LocalDate soat) {
}
