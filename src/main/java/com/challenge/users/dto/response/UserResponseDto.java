package com.challenge.users.dto.response;

import java.time.LocalDateTime;

import com.challenge.users.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
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
}
