package com.sometimes.sometimesbe.controller;

import com.sometimes.sometimesbe.dto.CardRequestDto;
import com.sometimes.sometimesbe.dto.CardResponseDto;
import com.sometimes.sometimesbe.dto.MessageResponseDto;
import com.sometimes.sometimesbe.security.UserDetailsImpl;
import com.sometimes.sometimesbe.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CardController {
    private final CardService cardService;

    @PostMapping("/cards")
    public ResponseEntity<CardResponseDto> createCard(@RequestBody CardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        //Board Entity에 requestdto 안의 값들을 받아서 createBoard 작업을 한다.
        ResponseEntity<CardResponseDto> card_post =cardService.createCard(requestDto, userDetails.getUser());
        return card_post;
    }

    // 카드 조회
    @GetMapping("/cards")
    public ResponseEntity <List<CardResponseDto>> getCards() {
        return cardService.getCards();
    }

    // 카드 조회
    @GetMapping("/cards/{id}")
    public ResponseEntity<CardResponseDto> getCard(@PathVariable Long id) {
        return cardService.getCard(id);
    }

    // 카드 삭제
    @DeleteMapping("/cards/{id}")
    public ResponseEntity<MessageResponseDto> deleteCard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.deleteCard(id, userDetails.getUser());
    }

    // 카드 좋아요
    @PostMapping("/cards/likes/{id}")
    public ResponseEntity<MessageResponseDto> createLike(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.createLike(id, userDetails.getUser());
    }

    @PutMapping("/cards/{id}")
    public ResponseEntity<CardResponseDto> updateCard(@PathVariable Long id,@RequestBody CardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        ResponseEntity<CardResponseDto> card_update = cardService.updateCard(id, requestDto, userDetails.getUser());
        return card_update;
    }

}
