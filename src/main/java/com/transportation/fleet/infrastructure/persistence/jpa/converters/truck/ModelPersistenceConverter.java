package com.transportation.fleet.infrastructure.persistence.jpa.converters.truck;

import com.transportation.fleet.domain.model.valueobjects.truck.ModelVo;
import jakarta.persistence.AttributeConverter;

public class ModelPersistenceConverter implements AttributeConverter<ModelVo, String> {

    @Override
    public String convertToDatabaseColumn(ModelVo attribute) {
        return attribute.value();
    }

    @Override
    public ModelVo convertToEntityAttribute(String dbData) {
        return new ModelVo(dbData);
    }
}
