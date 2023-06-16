package com.challenge.users.dto.request;

import com.challenge.users.entity.Phone;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class PhoneUserSingUpRequestDto {

    public Long number;
    public Integer citycode;
    public String contrycode;

    public PhoneUserSingUpRequestDto(Phone phone) {
        this.number = phone.getNumber();
        this.citycode = phone.getCitycode();
        this.contrycode = phone.getContrycode();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((number == null) ? 0 : number.hashCode());
        result = prime * result + ((citycode == null) ? 0 : citycode.hashCode());
        result = prime * result + ((contrycode == null) ? 0 : contrycode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PhoneUserSingUpRequestDto other = (PhoneUserSingUpRequestDto) obj;
        if (number == null) {
            if (other.number != null)
                return false;
        } else if (!number.equals(other.number))
            return false;
        if (citycode == null) {
            if (other.citycode != null)
                return false;
        } else if (!citycode.equals(other.citycode))
            return false;
        if (contrycode == null) {
            if (other.contrycode != null)
                return false;
        } else if (!contrycode.equals(other.contrycode))
            return false;
        return true;
    }
    

}
