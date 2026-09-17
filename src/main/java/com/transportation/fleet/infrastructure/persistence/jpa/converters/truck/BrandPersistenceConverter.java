package com.transportation.fleet.infrastructure.persistence.jpa.converters.truck;

import com.transportation.fleet.domain.model.valueobjects.truck.BrandVo;
import jakarta.persistence.AttributeConverter;

public class BrandPersistenceConverter implements AttributeConverter<BrandVo,String> {

    @Override
    public String convertToDatabaseColumn(BrandVo attribute) {
        return attribute.value();
    }

    @Override
    public BrandVo convertToEntityAttribute(String dbData) {
        return new BrandVo(dbData);
    }
}
