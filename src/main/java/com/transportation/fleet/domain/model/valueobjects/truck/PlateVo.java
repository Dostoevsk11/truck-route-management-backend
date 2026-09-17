package com.transportation.fleet.domain.model.valueobjects.truck;

public record PlateVo(String value) {
    public PlateVo {
        if(value==null || value.isBlank())
            throw new IllegalArgumentException("Plate cannot be null or empty");
        value = value
                .trim()
                .toUpperCase()
                .replace("-", "");
        if(!value.matches("[A-Z]{3}[0-9]{3}"))
            throw new IllegalArgumentException("Plate must be in the format of AAA123");
    }
}
