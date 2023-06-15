package com.challenge.users.dto.request;

import com.challenge.users.entity.Phone;

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
public class PhoneUserSingUpRequestDto {

    public Long number;
    public Integer citycode;
    public String contrycode;

    public PhoneUserSingUpRequestDto(Phone phone) {
        this.number = phone.getNumber();
        this.citycode = phone.getCitycode();
        this.contrycode = phone.getContrycode();
    }

}
