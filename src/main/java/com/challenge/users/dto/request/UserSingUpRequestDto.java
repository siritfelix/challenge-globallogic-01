package com.challenge.users.dto.request;

import java.util.Set;
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
    public String email;
    public String password;
    public Set<PhoneUserSingUpRequestDto> phones;

}
