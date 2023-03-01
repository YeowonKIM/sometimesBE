package com.sometimes.sometimesbe.controller;

import com.sometimes.sometimesbe.dto.CardRequestDto;
import com.sometimes.sometimesbe.dto.CardResponseDto;
import com.sometimes.sometimesbe.dto.MessageResponseDto;
import com.sometimes.sometimesbe.security.UserDetailsImpl;
import com.sometimes.sometimesbe.service.CardService;
import com.sometimes.sometimesbe.service.CrawlingService;
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

    // 카드 생성
    @PostMapping("/cards")
    public ResponseEntity<CardResponseDto> createCard(@RequestBody CardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {

        return cardService.createCard(requestDto, userDetails.getUser());
    }

    // 카드 전체 조회
    @GetMapping("/cards")
    public ResponseEntity <List<CardResponseDto>> getCards() {
        return cardService.getCards();
    }

    // 카드 선택 조회
    @GetMapping("/cards/{id}")
    public ResponseEntity<CardResponseDto> getCard(@PathVariable Long id) {
        return cardService.getCard(id);
    }

    // 카드 삭제
    @DeleteMapping("/cards/{id}")
    public ResponseEntity<MessageResponseDto> deleteCard(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.deleteCard(id, userDetails.getUser());
    }

    // 카드 수정
    @PutMapping("/cards/{id}")
    public ResponseEntity<CardResponseDto> updateCard(@PathVariable Long id, @RequestBody CardRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.updateCard(id, requestDto, userDetails.getUser());
    }

    // 카드 좋아요
    @PutMapping("/cards/likes/{id}")
    public ResponseEntity<MessageResponseDto> createLike(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return cardService.createLike(id, userDetails.getUser());
    }


}
