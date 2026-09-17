package com.transportation.fleet.domain.model.valueobjects.truck;

import java.time.Year;

public record YearVo(int value) {
    public YearVo {
        int currentYear = Year.now().getValue();
        if (value < 1900 || value > currentYear) {
            throw new IllegalArgumentException("Year must be between 1900 and " + currentYear);
        }
    }
}
