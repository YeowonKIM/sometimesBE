package com.sometimes.sometimesbe.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MessageResponseDto {
    private String msg;
    private int statusCode;

    @Builder
    private MessageResponseDto(String msg, int statusCode)
    {
        this.msg = msg;
        this.statusCode = statusCode;

    }

    public static MessageResponseDto of(HttpStatus status, String msg)
    {
        return MessageResponseDto.builder()
                .statusCode(status.value())
                .msg(msg)
                .build();
    }


}
