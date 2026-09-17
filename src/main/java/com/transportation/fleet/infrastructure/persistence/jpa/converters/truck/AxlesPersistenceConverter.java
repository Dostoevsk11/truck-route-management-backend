package com.transportation.fleet.infrastructure.persistence.jpa.converters.truck;

import com.transportation.fleet.domain.model.valueobjects.truck.AxlesVo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class AxlesPersistenceConverter implements AttributeConverter<AxlesVo, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AxlesVo attribute) {
        return attribute == null ? null : attribute.value();
    }

    @Override
    public AxlesVo convertToEntityAttribute(Integer dbData) {
        return dbData == null ? null : new AxlesVo(dbData);
    }
}
