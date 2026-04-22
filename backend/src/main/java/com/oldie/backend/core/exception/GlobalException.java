package com.oldie.backend.core.exception;

import com.oldie.backend.core.dto.ApiResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;

@ControllerAdvice
public class GlobalException {

    ApiResponse<String> formatErrorResponse(AppException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        return ApiResponse.error(errorCode.getCode(), errorCode.name(), errorCode.getMessage());
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ApiResponse<String>> handleAllExceptions(AppException ex) {
        ApiResponse<String> response = formatErrorResponse(ex);
        return ResponseEntity.status(ex.getErrorCode().getCode()).body(response);
    }
}