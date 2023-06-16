package com.challenge.users.dto.response;

import java.time.LocalDateTime;

import com.challenge.users.entity.User;

import lombok.Getter;

@Getter
public class UserResponseDto {
    private String id;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
    private String token;
    private Boolean isActive;

    public UserResponseDto(User user) {
        this.id = user.getId().toString();
        this.created = user.getCreated();
        this.lastLogin = user.getLastLogin();
        this.token = null;
        this.isActive = user.getIsActive();
    }

    public void setToken(String token) {
        this.token = token;
    }
}
