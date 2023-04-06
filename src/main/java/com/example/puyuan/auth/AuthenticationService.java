package com.example.puyuan.auth;

import com.example.puyuan.base.StatusResponse;
import com.example.puyuan.config.JwtService;
import com.example.puyuan.user.Role;
import com.example.puyuan.user.User;
import com.example.puyuan.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final PasswordEncoder encoder;

    public StatusResponse register(RegisterRequest request) {
        var user = User.builder()
                .account(request.getAccount())
                .phone(request.getPhone())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        // 儲存至資料庫
        repository.save(user);

        return StatusResponse.SUCCESS();
    }

    public AuthenticationResponse login(AuthenticationRequest request){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getAccount(), request.getPassword()));
        var user = repository.findByEmail(request.getAccount())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public StatusResponse sendCode(VerificationCodeRequest request) {
        var user = repository.findByEmail(request.getAccount()).orElseThrow();
        var verificationCode = generateVerificationCode();
        user.setVerificationCode(verificationCode);
        repository.save(user);
        return StatusResponse.builder()
                .status(StatusResponse.Result.SUCCESS.getValue())
                .verificationCode(verificationCode)
                .build();
    }

    private String generateVerificationCode() {
        var codeSet = "0123456789";
        var random = new Random();
        var result = new StringBuilder();
        for(int i = 0; i < 6; i++) {
            result.append(codeSet.charAt(random.nextInt(codeSet.length())));
        }
        return result.toString();
    }
}
