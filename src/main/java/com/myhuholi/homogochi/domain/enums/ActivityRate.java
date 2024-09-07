package com.myhuholi.homogochi.domain.enums;

import jakarta.persistence.AttributeConverter;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@RequiredArgsConstructor
@Getter
public enum ActivityRate {
    LOW("Низкая активность", 0, "low"),
    NORMAL("Средняя активность", 1, "normal"),
    HIGH("Высокая активность", 2, "high"),
    ;

    private final String description;

    private final int value;
    private final String sysName;

    public static ActivityRate getByValue(int value) {
        for (ActivityRate activityRate : values()) {
            if (value == activityRate.value) {
                return activityRate;
            }
        }

        return null;
    }

    public static ActivityRate getBySysName(String sysName) {
        if (StringUtils.isBlank(sysName)) {
            return null;
        }

        for (ActivityRate value : values()) {
            if (sysName.equalsIgnoreCase(value.sysName)) {
                return value;
            }
        }

        return null;
    }

    public static class Converter implements AttributeConverter<ActivityRate, Integer> {
        @Override
        public Integer convertToDatabaseColumn(ActivityRate attribute) {
            return attribute.getValue();
        }

        @Override
        public ActivityRate convertToEntityAttribute(Integer dbData) {
            return ActivityRate.getByValue(dbData);
        }
    }
}
