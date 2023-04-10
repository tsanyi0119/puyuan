package com.example.puyuan.auth;

import com.example.puyuan.base.StatusResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService service;
    @PostMapping("/register")
    public ResponseEntity<StatusResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(service.login(request));
    }

    @Operation(summary = "發送驗證碼")
    @PostMapping("/verification/send")
    public ResponseEntity<StatusResponse> sendCode(
            @RequestBody VerificationCodeSendRequest request
    ) {
        return ResponseEntity.ok(service.sendCode(request));
    }

    @Operation(summary = "檢驗驗證碼")
    @PostMapping("/verification/check")
    public ResponseEntity<StatusResponse> checkCode(
            @RequestBody VerificationCodeCheckRequest request
    ){
        return ResponseEntity.ok(service.checkCode(request));
    }

    @Operation(summary = "忘記密碼")
    @PostMapping("/password/forgot")
    public ResponseEntity<StatusResponse> checkCode(
            @RequestBody PasswordForgotRequest request
    ){
        return ResponseEntity.ok(service.forgotPassword(request));
    }

    @Operation(summary = "重設密碼")
    @PostMapping("/password/reset")
    public ResponseEntity<StatusResponse> resetPassword(
            @RequestBody  PasswordResetRequest request
    ) {
        return ResponseEntity.ok(service.resetPassword(request));
    }
}
