package com.myhuholi.homogochi.domain.enums;

import com.myhuholi.homogochi.exception.EntityNotFoundException;
import jakarta.persistence.AttributeConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
@Getter
public enum Sex {
    MALE("MALE", "Мужской"),
    FEMALE("FEMALE", "Женский")
    ;

    private final String sysName;
    private final String description;

    public static Sex getBySysName(String sexSysName) {
        if (StringUtils.isBlank(sexSysName)) {
            throw new EntityNotFoundException(EntityName.SEX);
        }

        for (Sex value : values()) {
            if (sexSysName.equalsIgnoreCase(value.sysName)) {
                return value;
            }
        }

        throw new EntityNotFoundException(EntityName.SEX);
    }

    public static class Converter implements AttributeConverter<Sex, String> {

        @Override
        public String convertToDatabaseColumn(Sex attribute) {
            return attribute.sysName;
        }

        @Override
        public Sex convertToEntityAttribute(String dbData) {
            return Sex.getBySysName(dbData);
        }
    }
}
