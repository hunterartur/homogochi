package com.myhuholi.homogochi.exception;

import com.myhuholi.homogochi.storage.StorageError;
import lombok.Getter;

@Getter
public class StorageException extends RuntimeException{
    private Integer code = StorageError.STORAGE_ERROR.getCode();

    public StorageException(String message) {
        super(message);
    }

    public StorageException(String message, Throwable cause) {
        super(message, cause);
    }

    public StorageException(String message, Throwable cause, Integer code) {
        super(message, cause);
        this.code = code;
    }

    public StorageException(String message, Throwable cause, StorageError errorType) {
        super(message, cause);
        this.code = errorType == null ? null : errorType.getCode();
    }

    public StorageException(String message, StorageError errorType) {
        super(message);
        this.code = errorType == null ? null : errorType.getCode();
    }
}
