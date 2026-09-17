package com.transportation.fleet.domain.model.valueobjects.truck;

import java.math.BigDecimal;
import java.math.RoundingMode;

public record LoadCapacityVo(BigDecimal value) {
    public LoadCapacityVo {
        if (value == null) {
            throw new IllegalArgumentException("Load capacity cannot be null");
        }
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Load capacity must be greater than zero");
        }
        value = value.setScale(5, RoundingMode.HALF_UP);
    }
}
