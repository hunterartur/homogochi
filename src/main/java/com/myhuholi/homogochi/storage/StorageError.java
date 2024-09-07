package com.myhuholi.homogochi.storage;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum StorageError {
    STORAGE_ERROR(500),
    NOT_FOUND_ERROR(404);
    private final Integer code;
}
