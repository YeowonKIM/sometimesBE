package com.sometimes.sometimesbe.service;

import com.sometimes.sometimesbe.controller.CrawlingController;
import com.sometimes.sometimesbe.dto.CardRequestDto;
import com.sometimes.sometimesbe.dto.CardResponseDto;
import com.sometimes.sometimesbe.dto.MessageResponseDto;
import com.sometimes.sometimesbe.entity.*;
import com.sometimes.sometimesbe.repository.CardLikeRepository;
import com.sometimes.sometimesbe.repository.CardRepository;

import com.sometimes.sometimesbe.repository.ImageRepository;
import com.sometimes.sometimesbe.utils.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.sometimes.sometimesbe.utils.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class CardService {
    private final CardRepository cardRepository;
    private final CardLikeRepository cardLikeRepository;
    private final ImageRepository imageRepository;

    // 카드 생성
    @Transactional
    public ResponseEntity<CardResponseDto> createCard(CardRequestDto requestDto, User user) {
        Optional<Image> image = imageRepository.findById((long) (int) (Math.random() * 39 + 1));
        if (image.isEmpty()) {
            throw new CustomException(NOT_FOUND_IMAGE);
        }
        Card card = Card.of(requestDto, user, image.get());
        cardRepository.saveAndFlush(card);
        return ResponseEntity.ok(CardResponseDto.from(card));
    }

    // 카드 전체 조회
    @Transactional
    public ResponseEntity<List<CardResponseDto>> getCards() {
        List<Card> cardList = cardRepository.findAllByOrderByCreatedAtDesc();
        List<CardResponseDto> cardResponseList = new ArrayList<>();

        for (Card card : cardList) {
            cardResponseList.add(CardResponseDto.from(card, cardLikeRepository.countCardLikeByCardId(card.getId())));
        }
        return ResponseEntity.ok().body(cardResponseList);
    }

    // 카드 선택 조회
    @Transactional
    public ResponseEntity<CardResponseDto> getCard(Long id) {
        Optional<Card> card = cardRepository.findById(id);
        if (card.isEmpty()) {
            throw new CustomException(NOT_FOUND_CARD);
        }
        return ResponseEntity.ok().body(CardResponseDto.from(card.get(), cardLikeRepository.countCardLikeByCardId(card.get().getId())));
    }

    // 카드 삭제
    @Transactional
    public ResponseEntity<MessageResponseDto> deleteCard(Long id, User user) {

        Optional<Card> card = cardRepository.findById(id);
        if (card.isEmpty()) {
            throw new CustomException(NOT_FOUND_CARD);
        }

        UserRoleEnum role = user.getRole();
        if (card.get().getUser().getId().equals(user.getId()) || role == UserRoleEnum.ADMIN) {
            cardLikeRepository.deleteByCardId(id);
            cardRepository.deleteById(id);
        } else {
            throw new CustomException(AUTHORIZATION);
        }

        return ResponseEntity.ok()
                .body(MessageResponseDto.of("글 삭제 완료", HttpStatus.OK));

    }

    @Transactional
    public ResponseEntity<CardResponseDto> updateCard(Long id, CardRequestDto requestDto, User user) {
        Optional<Card> card = cardRepository.findById(id);
        if (card.isEmpty()) {
            throw new CustomException(NOT_FOUND_CARD);
        }

        UserRoleEnum role = user.getRole();

        if (card.get().getUser().getId().equals(user.getId()) || role == UserRoleEnum.ADMIN) {
            card.get().update(requestDto);
        } else {
            throw new CustomException(AUTHORIZATION);
        }

        return ResponseEntity.ok()
                .body(CardResponseDto.from(card.get()));
    }

    // 카드 좋아요
    @Transactional
    public ResponseEntity<MessageResponseDto> createLike(Long id, User user) {

        Optional<Card> card = cardRepository.findById(id);
        if (card.isEmpty()) {
            throw new CustomException(NOT_FOUND_CARD);
        }

        Optional<CardLike> cardLike = cardLikeRepository.findByCardIdAndUserId(id, user.getId());
        if (cardLike.isEmpty()) {
            cardLikeRepository.saveAndFlush(CardLike.of(card.get(), user));
            return ResponseEntity.ok().body(MessageResponseDto.of("좋아요 추가", HttpStatus.OK));
        } else {
            cardLikeRepository.delete(cardLike.get());
            return ResponseEntity.ok().body(MessageResponseDto.of("좋아요 취소", HttpStatus.OK));
        }

    }


}
