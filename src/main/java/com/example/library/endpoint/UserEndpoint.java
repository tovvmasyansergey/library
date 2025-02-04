package com.example.library.endpoint;

import com.example.library.dto.UserAuthRequestDto;
import com.example.library.dto.UserAuthResponseDto;
import com.example.library.dto.UserRegisterRequestDto;
import com.example.library.dto.UserRegisterResponseDto;
import com.example.library.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserEndpoint {
    private final UserServiceImpl userServiceImpl;

    @PostMapping("/auth")
    public ResponseEntity<UserAuthResponseDto> auth(@RequestBody UserAuthRequestDto userAuthRequestDto) {
        return ResponseEntity.ok(userServiceImpl.authUserByEmailPassword(userAuthRequestDto));
    }

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponseDto> register(@RequestBody UserRegisterRequestDto userRegisterRequestDto) {
        return ResponseEntity.ok(userServiceImpl.register(userRegisterRequestDto));
    }
}
