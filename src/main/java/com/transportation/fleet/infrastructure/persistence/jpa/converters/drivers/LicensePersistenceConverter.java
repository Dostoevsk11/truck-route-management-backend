package com.transportation.fleet.infrastructure.persistence.jpa.converters.drivers;

import com.transportation.fleet.domain.model.valueobjects.drivers.LicenseVo;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class LicensePersistenceConverter implements AttributeConverter<LicenseVo, String> {

    @Override
    public String convertToDatabaseColumn(LicenseVo attribute) {
        return attribute == null ? null : attribute.value();
    }

    @Override
    public LicenseVo convertToEntityAttribute(String dbData) {
        return dbData == null ? null : new LicenseVo(dbData);
    }
}
