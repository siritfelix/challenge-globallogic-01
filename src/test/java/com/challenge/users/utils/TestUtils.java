package com.challenge.users.utils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.challenge.users.dto.request.PhoneUserSingUpRequestDto;
import com.challenge.users.dto.request.UserSingUpRequestDto;
import com.challenge.users.entity.User;
import com.challenge.users.service.impl.UserDetailsImpl;

public class TestUtils {

    public static UserSingUpRequestDto buildUserSingUpRequestDtoOk() {
        return new UserSingUpRequestDto("myname", "myemail@myemail.com", "A15assdtt",
                buildSetPhoneUserSingUpRequestDto(2));
    }

    public static PhoneUserSingUpRequestDto buildPhoneUserSingUpRequestDto(Long number) {
        return new PhoneUserSingUpRequestDto(123L, 1, "co");
    }

    public static Set<PhoneUserSingUpRequestDto> buildSetPhoneUserSingUpRequestDto(Integer size) {
        Set<PhoneUserSingUpRequestDto> phoneUserSingUpRequestDtos = new HashSet<>();
        for (int i = 0; i < size; i++) {
            phoneUserSingUpRequestDtos.add(new PhoneUserSingUpRequestDto(123L + i, 1, "co"));
        }
        return phoneUserSingUpRequestDtos;
    }

    public static UserSingUpRequestDto buildUserSingUpRequestDtoEmailIncorrect() {
        return new UserSingUpRequestDto("myname", "myemailmyemail.com", "A15assdtt",
                buildSetPhoneUserSingUpRequestDto(1));
    }

    public static UserSingUpRequestDto buildUserSingUpRequestDtoPasswordIncorrect() {
        return new UserSingUpRequestDto("myname", "myemail@myemail.com", "15assdtt",
                buildSetPhoneUserSingUpRequestDto(1));
    }

    public static UserSingUpRequestDto buildUserSingUpRequestDtoEmailAndPasswordIncorrect() {
        return new UserSingUpRequestDto("myname", "myemailmyemail.com", "15assdtt",
                buildSetPhoneUserSingUpRequestDto(1));
    }

    public static UserSingUpRequestDto buildUserSingUpRequestDtoOk2() {
        return new UserSingUpRequestDto("myname", "myemail2@myemail.com", "A15assdtt",
                buildSetPhoneUserSingUpRequestDto(1));
    }

    public static UserSingUpRequestDto buildUserSingUpRequestDtoOk3() {
        return new UserSingUpRequestDto("myname", "myemail3@myemail.com", "A15assdtt",
                buildSetPhoneUserSingUpRequestDto(1));
    }

    public static UserSingUpRequestDto buildUserSingUpRequestDtoOk4() {
        return new UserSingUpRequestDto("myname", "myemail4@myemail.com", "A15assdtt",
                buildSetPhoneUserSingUpRequestDto(1));
    }

    public static UserSingUpRequestDto buildUserSingUpRequestDtoOk5() {
        return new UserSingUpRequestDto("myname", "myemail5@myemail.com", "A15assdtt",
                buildSetPhoneUserSingUpRequestDto(1));
    }

    public static Set<PhoneUserSingUpRequestDto> buildSetPhoneUserSingUpRequestDtoEquals(Integer size) {
        Set<PhoneUserSingUpRequestDto> phoneUserSingUpRequestDtos = new HashSet<>();
        for (int i = 0; i < size; i++) {
            phoneUserSingUpRequestDtos.add(new PhoneUserSingUpRequestDto(123L, 1, "co"));
        }
        return phoneUserSingUpRequestDtos;
    }

    public static UserSingUpRequestDto buildUserSingUpRequestDtoEqualsPhones() {
        return new UserSingUpRequestDto("myname", "myemailequals@myemail.com", "A15assdtt",
                buildSetPhoneUserSingUpRequestDtoEquals(2));
    }

    public static String badrequest() {
        return "{\"name\": \"String\",\"email\": \"sirit@gmail.com\",\"password\": \"M12asdf1\",\"phones\": "
                +
                " [{\"number\": \"1234A\",\"citycode\": 1,\"contrycode\": \"DSS\"}, {\"number\": 1234,\"citycode\": 1,\"contrycode\":"
                +
                " \"DSS\"} ]}}";
    }

    public static String passwordEncoder() {
        return "$2a$10$MicLwmVqDqSk7EANgfhkL.rLf0qZ1RzAZKutuICRpidvJUaWt1Dx2";

    }

    public static User buildUserEntity(UserSingUpRequestDto userSingUpRequestDto) {
        return new User(userSingUpRequestDto);
    }

    public static String buildToken() {
        return "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzaXJpdEBnbWFpbC5jb20iLCJpYXQiOjE2ODY5Mjg1MDgsImV4cCI6MTY4NjkyODU2OH0.uSkuj104uss8i_T-wrCaa20_fiCz5MnNxTyUwPNkRjw";
    }

    public static Optional<User> buildUserEntityOptional() {
        return Optional.of(TestUtils.buildUserEntity(TestUtils.buildUserSingUpRequestDtoOk()));
    }

    public static String errorMessagerMapError() {
        return "Error";
    }

    public static Map<Integer, String> errorMessagerMap() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(400, "Bad request");
        map.put(401, "Unauthorized, check credentials");
        map.put(404, "Not found");
        map.put(409, "There is already a registered user with that email");
        map.put(500, "An error occurred");
        map.put(4001, "check field: ");
        return map;

    }

    public static final UserDetailsImpl buildUserDetailsImpl(User user) {
        return new UserDetailsImpl(user.getId(), user.getEmail(), user.getEmail(),
                user.getPassword(), user.getIsActive(), null);
    }
}