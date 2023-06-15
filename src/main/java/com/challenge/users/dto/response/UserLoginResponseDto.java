package com.challenge.users.dto.response;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.challenge.users.dto.request.PhoneUserSingUpRequestDto;
import com.challenge.users.entity.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class UserLoginResponseDto {
    private String id;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
    private String token;
    private Boolean isActive;
    private String name;
    private String email;
    private String password;
    private List<PhoneUserSingUpRequestDto> phones;

    public UserLoginResponseDto(User user) {
        this.id = user.getId().toString();
        this.created = user.getCreated();
        this.lastLogin = user.getLastLogin();
        this.isActive = user.getIsActive();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.phones = user.getPhones().stream().map(PhoneUserSingUpRequestDto::new).collect(Collectors.toList());
    }
}
