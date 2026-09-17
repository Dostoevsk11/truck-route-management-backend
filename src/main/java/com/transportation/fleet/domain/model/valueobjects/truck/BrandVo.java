package com.transportation.fleet.domain.model.valueobjects.truck;

public record BrandVo(String value) {
    public BrandVo {
        if (value == null || value.isBlank() || value.length() < 2 || value.length() > 20) {
            throw new IllegalArgumentException("Brand must contain between 2 and 20 characters");
        }
    }
}
