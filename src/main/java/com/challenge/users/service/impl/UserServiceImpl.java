package com.challenge.users.service.impl;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenge.users.config.ErrorMessagerMap;
import com.challenge.users.config.security.jwt.JwtUtils;
import com.challenge.users.dto.request.UserSingUpRequestDto;
import com.challenge.users.dto.response.UserLoginResponseDto;
import com.challenge.users.dto.response.UserResponseDto;
import com.challenge.users.entity.User;
import com.challenge.users.exceptions.ConflictException;
import com.challenge.users.exceptions.NotFoundException;
import com.challenge.users.repository.UserRepository;
import com.challenge.users.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ErrorMessagerMap errorMessagerMap;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public UserServiceImpl(UserRepository userRepository, ErrorMessagerMap errorMessagerMap,
            PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.errorMessagerMap = errorMessagerMap;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @Override
    public ResponseEntity<UserResponseDto> singUpUser(UserSingUpRequestDto userSingUpRequestDto) {

        if (userRepository.findOneByEmail(userSingUpRequestDto.getEmail()).isPresent()) {
            log.info(errorMessagerMap.getMessager().get(409).concat(": {}"), userSingUpRequestDto.getEmail());
            throw new ConflictException(409);
        }
        String password = userSingUpRequestDto.getPassword();
        userSingUpRequestDto.setPassword(passwordEncoder.encode(userSingUpRequestDto.getPassword()));
        UserResponseDto userResponseDto = new UserResponseDto(userRepository.save(new User(userSingUpRequestDto)));
        userResponseDto.setToken(this.authentication(userSingUpRequestDto.getEmail(), password));
        return new ResponseEntity<UserResponseDto>(userResponseDto, HttpStatus.OK);
    }

    @Transactional
    @Override
    public ResponseEntity<UserLoginResponseDto> loginUser(UserDetailsImpl userDetailsImpl)
            throws NotFoundException {
        User user = userRepository.findOneByEmail(userDetailsImpl.getEmail())
                .orElseThrow(() -> new NotFoundException(404));
        String token = jwtUtils.generateJwtToken(SecurityContextHolder.getContext().getAuthentication());
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
        UserLoginResponseDto userLoginResponseDto = new UserLoginResponseDto(user);
        userLoginResponseDto.setToken(token);
        return new ResponseEntity<>(
                userLoginResponseDto,
                HttpStatus.OK);

    }

    private String authentication(String email, String password) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        email,
                        password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        return jwt;
    }

}
