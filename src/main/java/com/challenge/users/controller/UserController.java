package com.challenge.users.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.users.dto.request.UserSingUpRequestDto;
import com.challenge.users.dto.response.UserLoginResponseDto;
import com.challenge.users.dto.response.UserResponseDto;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class UserController implements IUserController {

    @Override
    public ResponseEntity<UserResponseDto> singUpUser(@RequestBody UserSingUpRequestDto userSingUpRequestDto) {
        log.info("Request:{}", userSingUpRequestDto);
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'singUpUser'");
    }

    @Override
    public ResponseEntity<UserLoginResponseDto> loginUser(@RequestHeader("Authorization") String token) {
        log.info("Request:{}", token);
        throw new UnsupportedOperationException("Unimplemented method 'loginUser'");
    }

}
