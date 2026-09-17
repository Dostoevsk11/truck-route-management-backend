package com.transportation.fleet.infrastructure.persistence.jpa.converters.truck;

import com.transportation.fleet.domain.model.valueobjects.truck.SoatVo;
import jakarta.persistence.AttributeConverter;

import java.time.LocalDate;

public class SoatPersistenceConverter implements AttributeConverter<SoatVo, LocalDate> {

    @Override
    public LocalDate convertToDatabaseColumn(SoatVo attribute) {
        return attribute.value();
    }

    @Override
    public SoatVo convertToEntityAttribute(LocalDate dbData) {
        return new SoatVo(dbData);
    }
}
