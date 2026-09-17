package com.transportation.fleet.infrastructure.persistence.jpa.converters.drivers;

import com.transportation.fleet.domain.model.valueobjects.drivers.DniVo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class DniPersistenceConverter implements AttributeConverter<DniVo, String> {

    @Override
    public String convertToDatabaseColumn(DniVo attribute) {
        return attribute == null ? null : attribute.value();
    }

    @Override
    public DniVo convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new DniVo(dbData);
    }
}
