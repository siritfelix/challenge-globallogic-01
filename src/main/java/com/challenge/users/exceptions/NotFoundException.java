package com.challenge.users.exceptions;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private Integer code;

    public NotFoundException(Integer code) {
        this.code = code;
    }

}
