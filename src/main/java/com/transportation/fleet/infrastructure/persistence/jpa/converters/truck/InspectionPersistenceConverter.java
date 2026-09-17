package com.transportation.fleet.infrastructure.persistence.jpa.converters.truck;

import com.transportation.fleet.domain.model.valueobjects.truck.InspectionVo;
import jakarta.persistence.AttributeConverter;

import java.time.LocalDate;

public class InspectionPersistenceConverter implements AttributeConverter<InspectionVo, LocalDate> {

    @Override
    public LocalDate convertToDatabaseColumn(InspectionVo attribute) {
        return attribute.value();
    }

    @Override
    public InspectionVo convertToEntityAttribute(LocalDate dbData) {
        return new InspectionVo(dbData);
    }
}
