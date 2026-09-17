package com.transportation.fleet.domain.model.valueobjects.truck;

import java.time.LocalDate;
import java.util.Objects;

public record InspectionVo(LocalDate value) {
    public InspectionVo {
        Objects.requireNonNull(value, "Inspection date cannot be null");
    }
    public boolean isExpired() {
        return value.isBefore(LocalDate.now());
    }
}
