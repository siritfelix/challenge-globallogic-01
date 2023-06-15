package com.challenge.users.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.users.config.security.CurrentUser;
import com.challenge.users.dto.request.UserSingUpRequestDto;
import com.challenge.users.dto.response.UserLoginResponseDto;
import com.challenge.users.dto.response.UserResponseDto;
import com.challenge.users.service.UserService;
import com.challenge.users.service.impl.UserDetailsImpl;

import io.swagger.v3.oas.annotations.Parameter;

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

    @PreAuthorize("hasRole('ROLE_USER')")
    @Override
    public ResponseEntity<UserLoginResponseDto> loginUser(@RequestHeader("Authorization") String token,
            @CurrentUser @Parameter(hidden = true) UserDetailsImpl userDetailsImpl) {
        return userService.loginUser(userDetailsImpl);
    }

}
