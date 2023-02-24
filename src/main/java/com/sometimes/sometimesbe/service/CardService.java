package com.sometimes.sometimesbe.service;

import com.sometimes.sometimesbe.dto.CardRequestDto;
import com.sometimes.sometimesbe.dto.CardResponseDto;
import com.sometimes.sometimesbe.entity.Card;
import com.sometimes.sometimesbe.entity.User;
import com.sometimes.sometimesbe.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;

    @Transactional
    public ResponseEntity<CardResponseDto> createCard(CardRequestDto requestDto, User user) {
        Card card = Card.of(requestDto, user);
        cardRepository.save(card);
        CardResponseDto cardResponseDto = CardResponseDto.from(card);
        return ResponseEntity.ok(cardResponseDto);
    }
}
