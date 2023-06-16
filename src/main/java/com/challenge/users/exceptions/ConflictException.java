package com.challenge.users.exceptions;

import lombok.Getter;

@Getter
public class ConflictException extends RuntimeException {
    private Integer code;

    public ConflictException(Integer code) {
        this.code = code;
    }

}
