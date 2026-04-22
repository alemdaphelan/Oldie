package com.oldie.backend.core.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonInclude;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse<T> {
    Integer statusCode;
    String nameError;
    String message;
    T data;

    @Builder.Default
    LocalDateTime timestamp = LocalDateTime.now();

    public static <T> ApiResponse<T> success(T data) {
        return ApiResponse.<T>builder()
                .statusCode(200)
                .message("Success")
                .data(data)
                .build();
    }

    public static <T> ApiResponse<T> error(int code, String nameError, String message) {
        return ApiResponse.<T>builder()
                .statusCode(code)
                .nameError(nameError)
                .message(message)
                .build();
    }
}