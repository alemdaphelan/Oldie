package com.oldie.backend.core.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(500, "Something went wrong, please try again later."),
    USER_NOT_FOUND(404, "User not found."),
    USER_ALREADY_EXISTS(400, "User already exists."),
    INVALID_CREDENTIALS(401, "Invalid email or password.");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
