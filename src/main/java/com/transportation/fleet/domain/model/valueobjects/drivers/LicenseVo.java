package com.transportation.fleet.domain.model.valueobjects.drivers;

import java.util.Locale;

public record LicenseVo(String value) {
    public LicenseVo {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("License value cannot be null or empty");
        }
        value = value
                .trim().
                toUpperCase(Locale.ROOT);
        if(!value.matches("[A-Z][0-9]{8}")) {
            throw new IllegalArgumentException("License value must be in the format of A12345678");
        }
    }
}
