package com.myhuholi.homogochi.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EntityName {
    USER("User", "Пользователь"),
    HOMO_PICTURE("HomoPicture", "Картинка хомяка"),
    HOMO_STATE("HomoState", "Состояние хомяка");

    private final String sysName;
    private final String description;
}