package com.sometimes.sometimesbe.dto;

import com.sometimes.sometimesbe.entity.Card;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class CardResponseDto {

    private Long id;
    private String content;
    private String nickname;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private int likes;

    @Builder
    private CardResponseDto(Card card, int likes)
    {
        this.id = card.getId();
        this.content = card.getContent();
        this.nickname = card.getUser().getNickname();
        this.createdAt = card.getCreatedAt();
        this.modifiedAt = card.getModifiedAt();
        this.likes = likes;
    }

    public static CardResponseDto from(Card card, int likes)
    {
        return CardResponseDto.builder()
                .card(card)
                .likes(likes)
                .build();
    }

}
