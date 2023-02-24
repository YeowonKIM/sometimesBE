package com.sometimes.sometimesbe.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Getter
@Entity
@NoArgsConstructor
public class CardLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID",nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "CARD_ID",nullable = false)
    private Card card;


    @Builder
    private CardLike(Card card, User user)
    {
        this.card = card;
        this.user = user;

    }

    public static CardLike of(Card card, User user)
    {
        return CardLike.builder()
                .card(card)
                .user(user)
                .build();
    }

}
