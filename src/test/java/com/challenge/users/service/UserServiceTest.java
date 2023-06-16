package com.challenge.users.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.challenge.users.config.ErrorMessagerMap;
import com.challenge.users.config.security.jwt.JwtUtils;
import com.challenge.users.dto.response.UserLoginResponseDto;
import com.challenge.users.dto.response.UserResponseDto;
import com.challenge.users.exceptions.ConflictException;
import com.challenge.users.exceptions.NotFoundException;
import com.challenge.users.repository.UserRepository;
import com.challenge.users.service.impl.UserServiceImpl;
import com.challenge.users.utils.TestUtils;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    UserServiceImpl userServiceImpl;
    @Mock
    UserRepository userRepository;
    @Mock
    ErrorMessagerMap errorMessagerMap;
    @Mock
    PasswordEncoder passwordEncoder;
    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    JwtUtils jwtUtils;

    @DisplayName("User registration with correct data")
    @Test
    void sing_up_returnOk() {
        doReturn(Optional.empty()).when(userRepository).findOneByEmail(any());
        doReturn(TestUtils.passwordEncoder()).when(passwordEncoder).encode(any());
        doReturn(TestUtils.buildUserEntity(TestUtils.buildUserSingUpRequestDtoOk())).when(userRepository).save(any());
        doReturn(TestUtils.buildToken()).when(jwtUtils).generateJwtToken(any());
        ResponseEntity<UserResponseDto> userrResponseEntity = userServiceImpl
                .singUpUser(TestUtils.buildUserSingUpRequestDtoOk());
        assertNotNull(userrResponseEntity);
        assertEquals(userrResponseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(userrResponseEntity.getBody().getIsActive(), true);

    }

    @DisplayName("Existing user record")
    @Test
    void sing_up_returnError() {
        doReturn(TestUtils.buildUserEntityOptional()).when(userRepository).findOneByEmail(any());
        doReturn(TestUtils.errorMessagerMap()).when(errorMessagerMap).getMessager();
        assertThrows(ConflictException.class, () -> userServiceImpl
                .singUpUser(TestUtils.buildUserSingUpRequestDtoOk()));

    }

    @DisplayName("Login ok")
    @Test
    void login_returnOk() {
        doReturn(TestUtils.buildUserEntityOptional()).when(userRepository).findOneByEmail(any());
        doReturn(TestUtils.buildToken()).when(jwtUtils).generateJwtToken(any());
        doReturn(TestUtils.buildUserEntity(TestUtils.buildUserSingUpRequestDtoOk())).when(userRepository).save(any());
        TestUtils.buildUserDetailsImpl(TestUtils.buildUserEntity(TestUtils.buildUserSingUpRequestDtoOk()));
        ResponseEntity<UserLoginResponseDto> userLoginResponseDtoResponseEntity = userServiceImpl
                .loginUser(TestUtils
                        .buildUserDetailsImpl(TestUtils.buildUserEntity(TestUtils.buildUserSingUpRequestDtoOk())));
        assertNotNull(userLoginResponseDtoResponseEntity);
        assertEquals(userLoginResponseDtoResponseEntity.getStatusCode(), HttpStatus.OK);
        assertEquals(userLoginResponseDtoResponseEntity.getBody().getIsActive(), true);
        assertEquals(userLoginResponseDtoResponseEntity.getBody().getToken(), TestUtils.buildToken());
    }

    @DisplayName("Login no found")
    @Test
    void login_returnNotFound() {
        doReturn(Optional.empty()).when(userRepository).findOneByEmail(any());
        assertThrows(NotFoundException.class, () -> userServiceImpl.loginUser(TestUtils
                .buildUserDetailsImpl(TestUtils.buildUserEntity(TestUtils.buildUserSingUpRequestDtoOk()))));
    }
}
