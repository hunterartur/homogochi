package com.myhuholi.homogochi.exception.advice;

import com.myhuholi.homogochi.dto.response.ErrorRes;
import com.myhuholi.homogochi.exception.EntityNotFoundException;
import com.myhuholi.homogochi.exception.StorageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StorageAdvice {

    @ExceptionHandler(StorageException.class)
    public ResponseEntity<ErrorRes> catchTaskException(StorageException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        if (ex.getCode() != null) {
            status = HttpStatus.valueOf(ex.getCode());
        }
        return ResponseEntity
                .status(status)
                .body(new ErrorRes(ex.getMessage(), status.value()));
    }
}
