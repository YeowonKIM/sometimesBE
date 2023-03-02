package com.sometimes.sometimesbe.dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class MessageResponseDto {
    private String msg;
    private int statusCode;
    private String nickname;

    @Builder
    private MessageResponseDto(String msg, int statusCode, String nickname) {
        this.msg = msg;
        this.statusCode = statusCode;
        this.nickname = nickname;
    }

    public static MessageResponseDto of(String msg, HttpStatus status) {
        return MessageResponseDto.builder()
                .msg(msg)
                .statusCode(status.value())
                .build();
    }

    public static MessageResponseDto of(String msg, HttpStatus status, String nickname) {
        return MessageResponseDto.builder()
                .msg(msg)
                .statusCode(status.value())
                .nickname(nickname)
                .build();
    }

}
