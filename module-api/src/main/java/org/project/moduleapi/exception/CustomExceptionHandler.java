package org.project.moduleapi.exception;

import lombok.extern.slf4j.Slf4j;
import org.project.modulecommon.exception.CustomException;
import org.project.modulecommon.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ErrorResponse errorResponse = ErrorResponse.builder()
            .code(e.getErrorCode().getCode())
            .message(e.getMessage())
            .build();

        log.error(e.toString());

        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
            .body(errorResponse);
    }
}
