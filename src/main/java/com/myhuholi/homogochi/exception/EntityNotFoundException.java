package com.myhuholi.homogochi.exception;

import com.myhuholi.homogochi.domain.enums.EntityName;

public class EntityNotFoundException extends RuntimeException {
    private static final String messageFormat = "Сущность \"%s\" (\"%s\") не найдена";

    public EntityNotFoundException(EntityName entityName) {
        super(String.format(messageFormat, entityName.getDescription(), entityName.getSysName()));
    }
}
