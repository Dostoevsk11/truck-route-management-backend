package com.transportation.fleet.infrastructure.persistence.jpa.converters.truck;

import com.transportation.fleet.domain.model.valueobjects.truck.TypeVo;
import jakarta.persistence.AttributeConverter;

public class TypePersistenceConverter implements AttributeConverter<TypeVo, String> {

    @Override
    public String convertToDatabaseColumn(TypeVo attribute) {
        return attribute.value();
    }

    @Override
    public TypeVo convertToEntityAttribute(String dbData) {
        return new TypeVo(dbData);
    }
}
