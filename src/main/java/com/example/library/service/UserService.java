package com.example.library.service;

import com.example.library.dto.UserAuthRequestDto;
import com.example.library.dto.UserAuthResponseDto;
import com.example.library.dto.UserRegisterRequestDto;
import com.example.library.dto.UserRegisterResponseDto;


public interface UserService {
    UserAuthResponseDto authUserByEmailPassword(UserAuthRequestDto userAuthRequestDto);
    UserRegisterResponseDto register(UserRegisterRequestDto userRegisterRequestDto);
}
