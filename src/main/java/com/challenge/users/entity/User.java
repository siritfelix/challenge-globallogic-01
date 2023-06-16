package com.challenge.users.entity;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.challenge.users.dto.request.UserSingUpRequestDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "USER_ENTITY")
public class User {

    @Id    
    @Column(unique = true)
    private String id;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
    private Boolean isActive;
    public String name;
    @Column(unique = true)
    public String email;
    @Size(max = 120)
    public String password;
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userEntity")
    public Set<Phone> phones;

    public User(UserSingUpRequestDto userSingUpRequestDto) {
        this.id = UUID.randomUUID().toString();
        this.created = LocalDateTime.now();
        this.lastLogin = null;
        this.isActive = true;
        this.name = userSingUpRequestDto.getName();
        this.email = userSingUpRequestDto.getEmail();
        this.password = userSingUpRequestDto.getPassword();
        this.phones = userSingUpRequestDto.getPhones().stream().map(Phone::new).map(phone -> {
            phone.setUserEntity(this);
            return phone;
        }).collect(Collectors.toSet());
    }
}
