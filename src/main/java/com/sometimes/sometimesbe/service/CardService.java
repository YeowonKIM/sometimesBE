package com.sometimes.sometimesbe.service;

import com.sometimes.sometimesbe.dto.CardRequestDto;
import com.sometimes.sometimesbe.dto.CardResponseDto;
import com.sometimes.sometimesbe.dto.MessageResponseDto;
import com.sometimes.sometimesbe.entity.Card;
import com.sometimes.sometimesbe.entity.CardLike;
import com.sometimes.sometimesbe.entity.User;
import com.sometimes.sometimesbe.entity.UserRoleEnum;
import com.sometimes.sometimesbe.repository.CardLikeRepository;
import com.sometimes.sometimesbe.repository.CardRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    private final CardLikeRepository cardLikeRepository;

    @Transactional
    public ResponseEntity<CardResponseDto> createCard(CardRequestDto requestDto, User user) {
        Card card = Card.of(requestDto, user);
        cardRepository.save(card);
        CardResponseDto cardResponseDto = CardResponseDto.from(card);
        return ResponseEntity.ok(cardResponseDto);
    }

    // 카드 전체 조회
    @Transactional
    public ResponseEntity <List<CardResponseDto>> getCards() {
        List<Card> cardList = cardRepository.findAllByOrderByCreatedAtDesc();
        List<CardResponseDto> cardResponseList = new ArrayList<>();

        for (Card card : cardList) {
            cardResponseList.add(CardResponseDto.from(card, cardLikeRepository.countCardLikeByCard_Id(card.getId())));
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

    // 카드 삭제
    @Transactional
    public ResponseEntity<MessageResponseDto> deleteCard(Long id, User user) {

        Optional<Card> card = cardRepository.findById(id);

        UserRoleEnum role = user.getRole();

        if(card.get().getUser().getId().equals(user.getId()) || role == UserRoleEnum.ADMIN) {
            cardLikeRepository.deleteByCardId(id);
            cardRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("삭제할 권한이 없습니다.");
        }

        return ResponseEntity.ok()
                .body(MessageResponseDto.of("글 삭제 완료", HttpStatus.OK));

    }

    @Transactional
    public ResponseEntity<CardResponseDto> updateCard(Long id, CardRequestDto requestDto, User user) {
        Card card = cardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("findContentThrow : 카드 콘텐트가 존재하지 않습니다.")
        );
        //글쓴 유저 아이디와, 실제 로그인 아이디 비교.
        if(!user.getUsername().equals(card.getUser().getUsername())){
            return ResponseEntity.badRequest().body(CardResponseDto.from(card));
        }
        card.update(requestDto);
        //성공했을 때.
        return ResponseEntity.ok(CardResponseDto.from(card));
    }

    // 카드 좋아요
    @Transactional
    public ResponseEntity<MessageResponseDto> createLike(Long id, User user) {
        Card card = cardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 카드가 없습니다.")
        );

        Optional<CardLike> cardLike = cardLikeRepository.findByCardIdAndUserId(id, user.getId());
        if (cardLike.isEmpty()) {  // 좋아요
            cardLikeRepository.saveAndFlush(CardLike.of(card, user));
            return ResponseEntity.ok().body(MessageResponseDto.of("좋아요 추가", HttpStatus.OK));
        } else {  // 좋아요 취소
            cardLikeRepository.delete(cardLike.get());
            return ResponseEntity.ok().body(MessageResponseDto.of("좋아요 취소",HttpStatus.OK));
        }

    }


}
