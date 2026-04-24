package com.oldie.backend.authentication;

import com.oldie.backend.authentication.dto.UserResponse;
import com.oldie.backend.authentication.dto.LoginRequest;
import com.oldie.backend.authentication.dto.RegisterRequest;
import com.oldie.backend.core.dto.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseCookie;
import org.springframework.http.HttpHeaders;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
class AuthController {
    private final AuthService authService;

    @Value("${jwt.expiration}")
    private long expiration;

    private ResponseCookie createTokenCookie(String token) {
        return ResponseCookie.from("token", token)
                .httpOnly(true)
                .secure(false)
                .maxAge(expiration / 1000)
                .path("/") // 7
                .sameSite("Lax")
                .build();
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> register(@Valid @RequestBody RegisterRequest request) {
        UserResponse response = authService.register(request);
        ResponseCookie cookies = createTokenCookie(response.getToken());
        return ResponseEntity.status(201).header(HttpHeaders.SET_COOKIE, cookies.toString())
                .body(ApiResponse.success(response));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponse>> login(@Valid @RequestBody LoginRequest request) {
        UserResponse response = authService.login(request);
        ResponseCookie cookies = createTokenCookie(response.getToken());
        return ResponseEntity.status(200).header(HttpHeaders.SET_COOKIE, cookies.toString())
                .body(ApiResponse.success(response));
    }

    @GetMapping("/oauth/{provider_name}/callback")
    public ResponseEntity<ApiResponse<UserResponse>> oauth(@PathVariable String providerName) {
        return null;
    }
}