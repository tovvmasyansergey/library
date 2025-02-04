package com.example.library.service.impl;

import com.example.library.dto.UserAuthRequestDto;
import com.example.library.dto.UserAuthResponseDto;
import com.example.library.dto.UserRegisterRequestDto;
import com.example.library.dto.UserRegisterResponseDto;
import com.example.library.entity.User;
import com.example.library.entity.UserType;
import com.example.library.exception.EmailValidationException;
import com.example.library.exception.EntityNotFoundException;
import com.example.library.mapper.UserMapper;
import com.example.library.repository.UserRepository;
import com.example.library.util.JwtTokenUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @InjectMocks
    UserServiceImpl userServiceImpl;
    @Mock
    UserRepository userRepository;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    JwtTokenUtil jwtTokenUtil;
    @Mock
    UserMapper userMapper;
    private User user;
    private UserRegisterRequestDto registerRequestDto;
    private UserAuthRequestDto authRequestDto;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setEmail("aaa");
        user.setPassword("aaa");

        registerRequestDto = new UserRegisterRequestDto("aa", "bb", "aaa", "aaa");
        authRequestDto = new UserAuthRequestDto("aaa", "aaa");
    }

    @Test
    void authUserByEmailPassword_SuccessTest() {
        when(userRepository.findByEmail("aaa")).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(user.getPassword(), authRequestDto.password())).thenReturn(true);
        when(jwtTokenUtil.generateToken(authRequestDto.email())).thenReturn("mockToken");

        UserAuthResponseDto userAuthResponseDto = userServiceImpl.authUserByEmailPassword(authRequestDto);

        assertNotNull(userAuthResponseDto);
        assertEquals("mockToken", userAuthResponseDto.token());
    }

    @Test
    void authUserByEmailPassword_UserNotFoundTest() {
        when(userRepository.findByEmail(authRequestDto.email())).thenReturn(Optional.empty());
        EmailValidationException emailValidationException = assertThrows(EmailValidationException.class, () -> userServiceImpl.authUserByEmailPassword(authRequestDto));
        assertEquals(String.format("User with %s email not exist", authRequestDto.email()), emailValidationException.getMessage());
    }

    @Test
    void authUserByEmailPassword_InvalidPasswordTest() {
        when(userRepository.findByEmail(authRequestDto.email())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(authRequestDto.password(), user.getPassword())).thenReturn(false);

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> userServiceImpl.authUserByEmailPassword(authRequestDto));
        assertEquals("Incorrect email or password", exception.getMessage());
    }

    @Test
    void register_SuccessTest() {
        when(userMapper.mapToUser(registerRequestDto)).thenReturn(user);
        when(passwordEncoder.encode(registerRequestDto.password())).thenReturn("aaaa");
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.mapToDto(user)).thenReturn(new UserRegisterResponseDto(1, "a", "a", "aaa", UserType.USER));

        UserRegisterResponseDto register = userServiceImpl.register(registerRequestDto);
        assertNotNull(register);
        assertEquals("aaa", register.email());
    }

    @Test
    void register_UserExist() {
        when(userRepository.existsByEmail("aaa")).thenReturn(true);
        EmailValidationException exception = assertThrows(EmailValidationException.class,
                () -> userServiceImpl.register(registerRequestDto));
        assertEquals(String.format("User with %s email already exist", registerRequestDto.email()), exception.getMessage());
    }
}