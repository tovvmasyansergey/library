package com.example.library.service.impl;

import com.example.library.dto.UserAuthRequestDto;
import com.example.library.dto.UserAuthResponseDto;
import com.example.library.dto.UserRegisterRequestDto;
import com.example.library.dto.UserRegisterResponseDto;
import com.example.library.entity.User;
import com.example.library.exception.EmailValidationException;
import com.example.library.exception.EntityNotFoundException;
import com.example.library.mapper.UserMapper;
import com.example.library.repository.UserRepository;
import com.example.library.service.UserService;
import com.example.library.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserMapper userMapper;


    @Override
    public UserAuthResponseDto authUserByEmailPassword(UserAuthRequestDto userAuthRequestDto) {
        User userByEmail = findUserByEmail(userAuthRequestDto.email());
        if (!passwordEncoder.matches(userAuthRequestDto.password(), userByEmail.getPassword())) {
            throw new EntityNotFoundException("Incorrect email or password");
        }
        String token = jwtTokenUtil.generateToken(userAuthRequestDto.email());
        return new UserAuthResponseDto(token);
    }
    @Override
    public UserRegisterResponseDto register(UserRegisterRequestDto userRegisterRequestDto) {
        validateUserRequest(userRegisterRequestDto);
        User user = userMapper.mapToUser(userRegisterRequestDto);
        user.setPassword(passwordEncoder.encode(userRegisterRequestDto.password()));
        User save = userRepository.save(user);
        return userMapper.mapToDto(save);
    }

    private void validateUserRequest(UserRegisterRequestDto userRegisterRequestDto) {
        if (userRegisterRequestDto == null) {
            throw new EntityNotFoundException("user is null");
        }
        if (existUserByEmail(userRegisterRequestDto.email())) {
            throw new EmailValidationException(String.format("User with %s email already exist", userRegisterRequestDto.email()));
        }
    }
    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EmailValidationException(String.format("User with %s email not exist", email)));
    }
    private boolean existUserByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}