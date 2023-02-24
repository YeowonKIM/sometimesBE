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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    public ResponseEntity <List<CardResponseDto>> getCards() {
        List<Card> cardList = cardRepository.findAllByOrderByCreatedAtDesc();
        List<CardResponseDto> cardResponseList = new ArrayList<>();

        for (Card card : cardList) {
            cardResponseList.add(CardResponseDto.from(card));
        }
        return ResponseEntity.ok().body(cardResponseList);
    }

    // 카드 선택 조회
    @Transactional
    public ResponseEntity<CardResponseDto> getCard(Long id) {
        Optional<Card> card = cardRepository.findById(id);
        if (card.isEmpty()) {
            throw new IllegalArgumentException("해당 카드가 없습니다.");
        }
        return ResponseEntity.ok()
                .body(CardResponseDto.from(card.get()));

    }

}
