package com.challenge.users.service;

import org.springframework.http.ResponseEntity;

import com.challenge.users.dto.request.UserSingUpRequestDto;
import com.challenge.users.dto.response.UserLoginResponseDto;
import com.challenge.users.dto.response.UserResponseDto;

public interface UserService {

    public ResponseEntity<UserResponseDto> singUpUser(UserSingUpRequestDto userSingUpRequestDto);

    public ResponseEntity<UserLoginResponseDto> loginUser(String token);
}
