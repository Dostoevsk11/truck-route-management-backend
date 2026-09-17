package com.transportation.fleet.interfaces.rest.resources;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(
        name = "TruckResource",
        description = "Truck information resource",
        example = """
                {
                  "id": 1,
                  "plate": "ABC-123",
                  "brand": "Volvo",
                  "model": "FH16",
                  "year": 2025,
                  "type": "TRACTOR_TRUCK",
                  "loadCapacity": 25000.00,
                  "axles": 3,
                  "truckStatus": "AVAILABLE",
                  "inspection": "2027-05-20",
                  "soat": "2027-08-15"
                }
                """
)
public record TruckResource(
        @Schema(description = "Truck unique identifier", example = "1")
        Long id,

        @Schema(description = "Truck license plate", example = "ABC-123")
        String plate,

        @Schema(description = "Truck brand", example = "Volvo")
        String brand,

        @Schema(description = "Truck model", example = "FH16")
        String model,

        @Schema(description = "Truck manufacturing year", example = "2025")
        int year,

        @Schema(description = "Truck type", example = "TRACTOR_TRUCK")
        String type,

        @Schema(
                description = "Truck load capacity in kilograms",
                example = "25000.00"
        )
        BigDecimal loadCapacity,

        @Schema(description = "Number of truck axles", example = "3")
        int axles,

        @Schema(description = "Truck current status", example = "AVAILABLE")
        String truckStatus,

        @Schema(
                description = "Technical inspection expiration date",
                example = "2027-05-20"
        )
        LocalDate inspection,

        @Schema(
                description = "SOAT expiration date",
                example = "2027-08-15"
        )
        LocalDate soat
) {}