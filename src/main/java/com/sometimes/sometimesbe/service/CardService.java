package com.sometimes.sometimesbe.service;

import com.sometimes.sometimesbe.dto.CardRequestDto;
import com.sometimes.sometimesbe.dto.CardResponseDto;
import com.sometimes.sometimesbe.dto.MessageResponseDto;
import com.sometimes.sometimesbe.entity.Card;
import com.sometimes.sometimesbe.entity.User;
import com.sometimes.sometimesbe.entity.UserRoleEnum;
import com.sometimes.sometimesbe.repository.CardRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional
    public ResponseEntity<MessageResponseDto> deleteCard(Long id, User user) {
        Optional<Card> card = cardRepository.findById(id);

        UserRoleEnum role = user.getRole();

        if(card.get().getUser().getId().equals(user.getId()) || role == UserRoleEnum.ADMIN) {
            cardRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("삭제할 권한이 없습니다.");
        }

        return ResponseEntity.ok()
                .body(MessageResponseDto.of(HttpStatus.OK, "글 삭제 완료"));

    }
}
