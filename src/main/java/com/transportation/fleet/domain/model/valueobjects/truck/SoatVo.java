package com.transportation.fleet.domain.model.valueobjects.truck;

import java.time.LocalDate;
import java.util.Objects;

public record SoatVo(LocalDate value) {
    public SoatVo {
        Objects.requireNonNull(value, "Expiration date cannot be null");
    }
    public boolean isExpired() {
        return value.isBefore(LocalDate.now());
    }
}
