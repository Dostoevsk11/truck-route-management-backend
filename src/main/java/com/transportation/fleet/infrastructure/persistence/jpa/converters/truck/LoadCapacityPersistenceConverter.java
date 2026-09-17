package com.transportation.fleet.infrastructure.persistence.jpa.converters.truck;

import com.transportation.fleet.domain.model.valueobjects.truck.LoadCapacityVo;
import jakarta.persistence.AttributeConverter;

import java.math.BigDecimal;

public class LoadCapacityPersistenceConverter implements AttributeConverter<LoadCapacityVo, BigDecimal> {

    @Override
    public BigDecimal convertToDatabaseColumn(LoadCapacityVo attribute) {
        return attribute.value();
    }

    @Override
    public LoadCapacityVo convertToEntityAttribute(BigDecimal dbData) {
        return new LoadCapacityVo(dbData);
    }
}
