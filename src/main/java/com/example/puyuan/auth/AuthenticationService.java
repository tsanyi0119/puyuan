package com.example.puyuan.auth;

import com.example.puyuan.user.Role;
import com.example.puyuan.user.User;
import com.example.puyuan.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .account(request.getAccount())
                .phone(request.getPhone())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(Role.USER)
                .build();
        // 儲存至資料庫
        repository.save(user);

        return AuthenticationResponse.SUCCESS();
    }
}
