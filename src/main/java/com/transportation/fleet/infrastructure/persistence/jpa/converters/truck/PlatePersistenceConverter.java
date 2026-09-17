package com.transportation.fleet.infrastructure.persistence.jpa.converters.truck;

import com.transportation.fleet.domain.model.valueobjects.truck.PlateVo;
import jakarta.persistence.AttributeConverter;

public class PlatePersistenceConverter implements AttributeConverter<PlateVo, String> {

    @Override
    public String convertToDatabaseColumn(PlateVo attribute) {
        return attribute.value();
    }

    @Override
    public PlateVo convertToEntityAttribute(String dbData) {
        return new PlateVo(dbData);
    }
}
