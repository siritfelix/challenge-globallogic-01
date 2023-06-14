package com.challenge.users.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.challenge.users.dto.request.PhoneUserSingUpRequestDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PHONE_ENTITY")
@ToString(exclude = "userEntity")
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Long number;
    public Integer citycode;
    public String contrycode;
    @JsonManagedReference
    @JoinColumn(name = "client_id", nullable = false, updatable = false, insertable = true)
    @ManyToOne
    public User userEntity;

    public Phone(PhoneUserSingUpRequestDto phoneUserSingUpRequestDto) {
        this.number = phoneUserSingUpRequestDto.getNumber();
        this.citycode = phoneUserSingUpRequestDto.getCitycode();
        this.contrycode = phoneUserSingUpRequestDto.getContrycode();
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
        Phone other = (Phone) obj;
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
