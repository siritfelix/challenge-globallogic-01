package com.challenge.users.service.impl;

import java.util.Iterator;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.challenge.users.config.ErrorMessagerMap;
import com.challenge.users.dto.request.UserSingUpRequestDto;
import com.challenge.users.dto.response.UserLoginResponseDto;
import com.challenge.users.dto.response.UserResponseDto;
import com.challenge.users.entity.Phone;
import com.challenge.users.entity.User;
import com.challenge.users.exceptions.ConflictException;
import com.challenge.users.repository.UserRepository;
import com.challenge.users.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ErrorMessagerMap errorMessagerMap;

    public UserServiceImpl(UserRepository userRepository, ErrorMessagerMap errorMessagerMap) {
        this.userRepository = userRepository;
        this.errorMessagerMap = errorMessagerMap;
    }

    @Override
    public ResponseEntity<UserResponseDto> singUpUser(UserSingUpRequestDto userSingUpRequestDto) {

        if (userRepository.findOneByEmail(userSingUpRequestDto.getEmail()).isPresent()) {
            log.info(errorMessagerMap.getMessager().get(409).concat(": {}"), userSingUpRequestDto.getEmail());
            throw new ConflictException(409);
        }
        UserResponseDto userResponseDto = new UserResponseDto(userRepository.save(new User(userSingUpRequestDto)));
        return new ResponseEntity<UserResponseDto>(userResponseDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserLoginResponseDto> loginUser(String token) {
        // TODO Auto-generated method stub
        return null;
    }

}
