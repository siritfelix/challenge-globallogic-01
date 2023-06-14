package com.challenge.users.dto.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

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
public class UserSingUpRequestDto {
    public String name;
    @Email
    public String email;
    @Pattern(regexp = "^(?=.*[A-Z])(?=(.*\\d){2})(?=.*[a-z])[A-Za-z\\d]{8,12}$")
    public String password;
    public Set<PhoneUserSingUpRequestDto> phones;

}
