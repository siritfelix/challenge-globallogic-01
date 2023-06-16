package com.challenge.users.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ErrorDto {
    private LocalDateTime timestamp;
    private Integer codigo;
    private String detail;
}
