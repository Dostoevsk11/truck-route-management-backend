package com.transportation.fleet.infrastructure.persistence.jpa.converters.truck;

import com.transportation.fleet.domain.model.valueobjects.truck.YearVo;
import jakarta.persistence.AttributeConverter;

public class YearPersistenceConverter implements AttributeConverter<YearVo, Integer> {

    @Override
    public Integer convertToDatabaseColumn(YearVo attribute) {
        return attribute.value();
    }

    @Override
    public YearVo convertToEntityAttribute(Integer dbData) {
        return new YearVo(dbData);
    }
}
