package com.challenge.users.entity;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.challenge.users.dto.request.UserSingUpRequestDto;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USER_ENTITY")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true)
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime lastLogin;
    private Boolean isActive;
    public String name;
    @Column(unique = true)
    public String email;
    public String password;
    @JsonBackReference
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "userEntity")
    public Set<Phone> phones;

    public User(UserSingUpRequestDto userSingUpRequestDto) {
        this.id = UUID.randomUUID();
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
