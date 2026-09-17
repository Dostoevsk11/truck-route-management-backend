package com.transportation.fleet.domain.model.valueobjects.drivers;

public record DniVo(String value) {
    public DniVo {
        if(value == null || value.isBlank()) {
            throw new IllegalArgumentException("DNI must not be null or blank");
        }
        value = value.trim();
        if(!value.matches("[0-9]{8}")){
            throw new IllegalArgumentException("DNI must be a valid number");
        }
    }
}
