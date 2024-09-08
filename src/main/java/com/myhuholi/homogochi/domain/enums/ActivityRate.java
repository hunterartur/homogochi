package com.myhuholi.homogochi.domain.enums;

import com.myhuholi.homogochi.exception.EntityNotFoundException;
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
        throw new EntityNotFoundException(EntityName.ACTIVITY_RATE);
    }

    public static ActivityRate getBySysName(String sysName) {
        if (StringUtils.isBlank(sysName)) {
            throw new EntityNotFoundException(EntityName.ACTIVITY_RATE);
        }

        for (ActivityRate value : values()) {
            if (sysName.equalsIgnoreCase(value.sysName)) {
                return value;
            }
        }

        throw new EntityNotFoundException(EntityName.ACTIVITY_RATE);
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
