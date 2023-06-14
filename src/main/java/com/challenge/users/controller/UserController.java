package com.challenge.users.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.users.dto.request.UserSingUpRequestDto;
import com.challenge.users.dto.response.UserLoginResponseDto;
import com.challenge.users.dto.response.UserResponseDto;
import com.challenge.users.service.UserService;

@RestController
public class UserController implements IUserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UserResponseDto> singUpUser(@Valid @RequestBody UserSingUpRequestDto userSingUpRequestDto) {
        return userService.singUpUser(userSingUpRequestDto);
    }

    @Override
    public ResponseEntity<UserLoginResponseDto> loginUser(@RequestHeader("Authorization") String token) {
        return userService.loginUser(token);
    }

}
