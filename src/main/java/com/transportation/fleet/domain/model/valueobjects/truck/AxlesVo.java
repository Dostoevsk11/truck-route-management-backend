package com.transportation.fleet.domain.model.valueobjects.truck;

public record AxlesVo(int value) {
    public AxlesVo {
        if(value <= 0 || value > 10) {
            throw new IllegalArgumentException("Axles must be greater than zero and less than or equal to 10");
        }
    }
}
