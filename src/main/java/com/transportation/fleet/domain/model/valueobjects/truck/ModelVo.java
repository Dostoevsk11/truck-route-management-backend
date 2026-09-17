package com.transportation.fleet.domain.model.valueobjects.truck;

public record ModelVo(String value) {
    public ModelVo {
        if (value == null || value.isBlank() || value.length() > 20 || value.length() < 2) {
            throw new IllegalArgumentException("Model must contain between 2 and 20 characters");
        }
    }
}
